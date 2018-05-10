using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System;
using System.IO;
using System.Threading;
using System.Text;
using System.Net;
using System.Net.Sockets;
//using ICSharpCode.SharpZipLib.Zip;
//using ICSharpCode.SharpZipLib.GZip;
//using LitJson;


public class BufferConnection{
	public Socket socket = null;
	public const int prefixSize = 4;
	public String ip = "127.0.0.1";
	public int port = 8888;
	
	private static ManualResetEvent connectDone = 
		new ManualResetEvent(false);
	private static ManualResetEvent sendDone = 
		new ManualResetEvent(false);
	private static ManualResetEvent receiveDone = 
		new ManualResetEvent(false);
	
	public BufferConnection(){
		
	}
	// State object for receiving data from remote device.
	public class StateObject {
		// Client socket.
		public Socket workSocket = null;
		// Size of receive buffer.
		public const int BufferSize = 1024;
		// Receive buffer.
		public byte[] buffer = new byte[BufferSize];
	}
	/**开始建立socket连接*/
	public void startConnect(){
		try{
			Debug.Log("starting connection...");
			IPAddress ipd = IPAddress.Parse(ip);
			EndPoint endPoint = new IPEndPoint(ipd,port);
			
			socket = new Socket(AddressFamily.InterNetwork,SocketType.Stream,ProtocolType.Tcp);
			socket.BeginConnect(endPoint,new AsyncCallback(connectCallback),socket);
			connectDone.WaitOne();
			
			receive(socket);
			//receiveDone.WaitOne();
			
		}catch(Exception e){
			Console.WriteLine(e.ToString());
		}
	}
	public void connectCallback(IAsyncResult ar){
		try{
			Socket backSocket = (Socket)ar.AsyncState;
			backSocket.EndConnect(ar);
			connectDone.Set();
			Debug.Log("on connected");
		}catch(Exception e){
			Console.WriteLine(e.ToString());   
		}  
	}
	/**发送数据，目前只支持 String 类型数据*/
	public void send(Socket client,String msg){
		//封装数据
		byte[] byteData = Encoding.UTF8.GetBytes(msg);
		byte[] sendData = new byte[byteData.Length + prefixSize];
		byte[] sizeData = BitConverter.GetBytes(byteData.Length);
		//反转
		Array.Reverse(sizeData);
		//合并
		System.Buffer.BlockCopy(sizeData,0,sendData,0,prefixSize);
		System.Buffer.BlockCopy(byteData,0,sendData,prefixSize,byteData.Length);
		try{
			//socket.Send(sendData);
			client.BeginSend(sendData,0,sendData.Length,0,new AsyncCallback(sendCallback),client);
			Debug.Log("data send finished, data size:"+sendData.Length);
		}catch(Exception e){
			Console.WriteLine(e.ToString());
		}
	}
	public void send(String msg){
		if(socket != null){
			send(socket,msg);
			sendDone.WaitOne();
		}
	}
	public void sendCallback(IAsyncResult ar){
		try{
			Socket socket = (Socket)ar.AsyncState;
			if(ar.IsCompleted){
				int endPoint = socket.EndSend(ar);  
				Debug.Log("send data finished endpoint: "+endPoint);
				sendDone.Set();
			}
		}catch(Exception e){
			Console.WriteLine(e.ToString());
		}
	}
	public void receive(Socket socket){
		try{   
			StateObject so = new StateObject();
			so.workSocket = socket;
			//第一次读取数据的总长度
			socket.BeginReceive(so.buffer,0,prefixSize,0,new AsyncCallback(receivedCallback),so);
			//测试用：数据在1024以内的数据，一次性读取出来
			//socket.BeginReceive(so.buffer,0,StateObject.BufferSize,0,new AsyncCallback(simpleCallback),so);
		}catch(Exception e){
			Console.WriteLine(e.ToString());
		}
	}
	public void simpleCallback(IAsyncResult ar){
		StateObject so = (StateObject)ar.AsyncState;  
		Socket socket = so.workSocket;
		byte[] presixBytes = new byte[prefixSize];
		int presix = 0;
		Buffer.BlockCopy(so.buffer,0,presixBytes,0,prefixSize);
		Array.Reverse(presixBytes);
		presix = BitConverter.ToInt32(presixBytes,0);
		if(presix <= 0){
			return;
		}  
		byte [] datas = new byte[presix];
		Buffer.BlockCopy(so.buffer,prefixSize,datas,0,datas.Length);
		String str = Encoding.UTF8.GetString(datas);
		Debug.Log("received message :"+str);
	}
	
	public MemoryStream receiveData = new MemoryStream();
	private bool isPresix = true;
	public int curPrefix = 0;//需要读取的数据总长度
	public void receivedCallback(IAsyncResult ar){
		try{
			StateObject so = (StateObject)ar.AsyncState;
			Socket client = so.workSocket;
			int readSize = client.EndReceive (ar);//结束读取，返回已读取的缓冲区里的字节数组长度
			//将每次读取的数据，写入内存流里
			receiveData.Write(so.buffer,0,readSize);
			receiveData.Position = 0;
			//读取前置长度，只读取一次
			if((int)receiveData.Length >= prefixSize && isPresix){
				byte[] presixBytes = new byte[prefixSize];
				receiveData.Read(presixBytes,0,prefixSize);
				Array.Reverse(presixBytes);
				curPrefix = BitConverter.ToInt32(presixBytes,0);
				isPresix = false;
			}
			if(receiveData.Length - (long)prefixSize < (long)curPrefix){
				//如果数据没有读取完毕，调整Position到最后，接着读取。
				receiveData.Position = receiveData.Length;
			}else{
				//如果内存流中的实际数字总长度符合要求，则说明数据已经全部读取完毕。
				//将position位置调整到第4个节点，开始准备读取数据。
				receiveData.Position = prefixSize;
				//读取数据
				byte[] datas = new byte[curPrefix];
				receiveData.Read(datas,0,datas.Length);
				//有压缩的话需要先解压，然后在操作。
				byte[] finallyBytes = decompress(datas);
				String str = Encoding.UTF8.GetString(finallyBytes);
				Debug.Log("the finally message is : "+str);
			}
			//重复读取，每次读取1024个字节数据
			client.BeginReceive(so.buffer,0,StateObject.BufferSize,0,new AsyncCallback(receivedCallback), so);
		}catch(Exception e){
			Console.WriteLine(e.ToString());
		}
	}
	private byte [] temp = new byte[1024];
	//解压
	public byte[] decompress(byte[] bytes){
		MemoryStream memory = new MemoryStream ();
		/*
		ICSharpCode.SharpZipLib.Zip.Compression.Inflater inf = new ICSharpCode.SharpZipLib.Zip.Compression.Inflater ();
		inf.SetInput (bytes);
		while (!inf.IsFinished) {
			int extracted = inf.Inflate (temp);
			if (extracted > 0) {
				memory.Write (temp, 0, extracted);
			} else {
				break; 
			}
		}*/
		return memory.ToArray ();
	}
}