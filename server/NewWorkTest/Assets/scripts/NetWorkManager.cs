namespace NetWork.Net
{
	using UnityEngine;   
	using System;   
	using System.Net.Sockets;   
	using System.Net;   
	using System.Collections;   
	using System.Text;   
	using NetWork.Type;   
	using System.Net.NetworkInformation;
	using System.IO;
	using System.Threading;

	public class SocketManager
	{
		public static Socket mSocket = null;   
		public static Socket mSocketLong = null;   
		byte[] data = null;

		public static Thread thread;
		
		public SocketManager()   
		{   
			
		}   
		public static void SocketConnection(string LocalIP, int LocalPort)   
		{   
			mSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);   
			try   
			{   
				IPAddress ip = IPAddress.Parse(LocalIP);   
				IPEndPoint ipe = new IPEndPoint(ip, LocalPort);   
				
				mSocket.Connect(ipe);  
			}   
			catch (Exception e)   
			{
                Debug.Log(""+e.Message);
				//ErrLog.RecordErr(e, ModuleName, "AsySocket", "");   
			}   
		}   

		public static void SocketConnectionLong(string LocalIP, int LocalPort)   
		{   
			mSocketLong = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);   
			try   
			{   
				IPAddress ip = IPAddress.Parse(LocalIP);   
				IPEndPoint ipe = new IPEndPoint(ip, LocalPort);   
				
				mSocketLong.Connect(ipe);  

				Thread thread = new Thread(new ThreadStart(ReceiveMsg));  
				thread.IsBackground = true;  
				thread.Start();  
			}   
			catch (Exception e)   
			{   
				//ErrLog.RecordErr(e, ModuleName, "AsySocket", "");   
			}   
		}   

		public static void getConnectionLong()
		{
			if (mSocketLong== null || !mSocketLong.Connected) 
			{
				SocketConnectionLong ("127.0.0.1", 8888);
				//与socket建立连接成功，开启线程接受服务端数据。  
//				Thread thread = new Thread(new ThreadStart(ReceiveMsg));  
//				thread.IsBackground = true;  
//				thread.Start(); 
			}
		}

		public static void CloseConnectionLong()
		{
			if (mSocketLong != null && mSocketLong.Connected) {
			
				closeSocketLong ();
				mSocketLong=null;
			}
		}



		public static void sendData (bool isCloseSocket = true)
		{
			if (!mSocket.Connected) 
			{
				Debug.Log ("businessComm clientSocket.Connected false");
				closeSocket ();
			}

			string playerid = "999";
			//if (GameMain.getInstance().getPlayer ().getAccountid() != null && GameMain.getInstance().getPlayer ().getAccountid() != "") 
			{
			//	playerid = GameMain.getInstance().getPlayer().getAccountid();
			}

			byte[] msgData = System.Text.Encoding.Default.GetBytes (playerid);
			int len = msgData.Length;

			//add head
			MemoryStream headStream = new MemoryStream (16+msgData.Length);
			//length
			headStream.Write (NetWork.Type.TypeConvert.getBytes(0,false), 0, 4);
			//int gameid
			headStream.Write (NetWork.Type.TypeConvert.getBytes(1,false), 0, 4);
			//int sessionid
//			headStream.Write (NetWork.Type.TypeConvert.getBytes(999,false), 0, 4);
			headStream.Write (NetWork.Type.TypeConvert.getBytes(len,false), 0, 4);
			headStream.Write (msgData,0,msgData.Length);

			//int packetCount
			headStream.Write (NetWork.Type.TypeConvert.getBytes(1,false), 0, 4);

			byte[] dataHead = headStream.GetBuffer ();


//			packetBody
			byte[] data = NetWork.NetIoStream.OutputStream.memStream.GetBuffer ();
			int bodyLength = data.Length-4;

			byte[] msgLengthBytes = NetWork.Type.TypeConvert.getBytes(bodyLength,false);

			for (int i=0; i<msgLengthBytes.Length; i++) 
			{
				data[i] = msgLengthBytes[i];
			}

			//Merge
			byte[] allData = new byte[dataHead.Length + data.Length];
			dataHead.CopyTo(allData, 0); 
			data.CopyTo(allData, dataHead.Length);

			int allLength = allData.Length-4;
			byte[] allLengthBytes = NetWork.Type.TypeConvert.getBytes(allLength,false);
			
			for (int i=0; i<allLengthBytes.Length; i++) 
			{
				allData[i] = allLengthBytes[i];
			}

			if (isCloseSocket) {
				mSocket.BeginSend (allData, 0, allData.Length, SocketFlags.None, new AsyncCallback (sendCallback), mSocket);
			} else {
//				mSocket.BeginSend (allData, 0, allData.Length, SocketFlags.None, new AsyncCallback (sendCallbackNoCloseSocket), mSocket);
			}
			NetWork.NetIoStream.OutputStream.memStream.Flush ();
			NetWork.NetIoStream.OutputStream.OutputStreamClear ();
		}

		public static void sendDataLong ()
		{
			if (!mSocketLong.Connected) 
			{
				Debug.Log ("businessComm clientSocket.Connected false");
				closeSocketLong ();
			}
			

			byte[] data = NetWork.NetIoStream.OutputStream.memStream.GetBuffer ();
            /*
			short bodyLength = (short)(data.Length-2);
			
			byte[] msgLengthBytes = NetWork.Type.TypeConvert.getBytes(bodyLength,true);
			
			for (int i=0; i<msgLengthBytes.Length; i++) 
			{
				data[i] = msgLengthBytes[i];
			}

			byte[] allData = new byte[data.Length];

			data.CopyTo(allData, 0);
			
			short allLength = (short)(allData.Length-2);
			byte[] allLengthBytes = NetWork.Type.TypeConvert.getBytes(allLength,true);
			
			for (int i=0; i<allLengthBytes.Length; i++) 
			{
				allData[i] = allLengthBytes[i];
			}
			*/
		
//			mSocket.BeginSend (allData, 0, allData.Length, SocketFlags.None, new AsyncCallback (sendCallback), mSocket);
//			Debug.Log (allData);
			//mSocketLong.Send (allData);
			mSocketLong.Send (data);
//			IAsyncResult asyncSend = mSocket.BeginSend(allData, 0, allData.Length, SocketFlags.None, new AsyncCallback(SendCallbackLong), mSocket);  
//			bool success = asyncSend.AsyncWaitHandle.WaitOne(5000, true);  
//			int bytesSent = NetWork.Net.SocketManager.mSocket.EndSend (asyncSend);
//			Debug.Log ("sendCallback: sent" + bytesSent);
 

			NetWork.NetIoStream.OutputStream.memStream.Flush ();
			NetWork.NetIoStream.OutputStream.OutputStreamClear ();
		}

		public static void SendCallbackLong(IAsyncResult asyncConnect)  
		{  
			Debug.Log("send success");  
		} 
		
		public static void sendCallback (IAsyncResult asyncConnect)
		{
			byte[] buff = new byte[1024*5];
			NetWork.Net.SocketManager.mSocket.Receive (buff);

			NetWork.NetIoStream.InputStream.load (buff);

			//length
			int len = NetWork.NetIoStream.InputStream.readInt ();
			//gameid
			int gameid = NetWork.NetIoStream.InputStream.readInt ();
			//sessionid
			int sessionid = NetWork.NetIoStream.InputStream.readInt ();
			//msgCount
			int msgcount = NetWork.NetIoStream.InputStream.readInt ();

			if (msgcount > 0) 
			{
				for( int i=0;i<msgcount;i++ )
				{
					int size = NetWork.NetIoStream.InputStream.readInt ();
//					Debug.Log("count="+i+"    msgsize="+size);
					byte[] sonDataBuffer = NetWork.NetIoStream.InputStream.inReader.ReadBytes(size);

					MemoryStream sonInStream = new MemoryStream (sonDataBuffer);
					BinaryReader sonInReader = new BinaryReader(sonInStream);  

					//NetWork.ProcessManager.NetPacketHand.ProcessHand( sonInReader);
				}
			}


			int bytesSent = NetWork.Net.SocketManager.mSocket.EndSend (asyncConnect);
//			Debug.Log ("sendCallback: sent" + bytesSent);

			NetWork.NetIoStream.InputStream.InputStreamClear ();

			closeSocket ();
		}

		public static void ReceiveMsg ()
		{
			while (true) {
				if( NetWork.Net.SocketManager.mSocketLong != null && NetWork.Net.SocketManager.mSocketLong.Connected )
				{

				    NetWork.NetIoStream.InputStreamTwo inputStream = new NetWork.NetIoStream.InputStreamTwo();

				    byte[] buff = new byte[1024 * 5];

                    int count = NetWork.Net.SocketManager.mSocketLong.Receive (buff);
                    Debug.Log("123456");
				
                    if (count > 0) {
			
					    inputStream.load (buff);
			
					    //length
					    short len = inputStream.readShort ();
                        //msgtype
                        short msgtype = inputStream.readShort();

                        if (len > 0 && msgtype > 0 ) {

                            Debug.Log("收到的长度：" + len);
                            byte[] bodydata = inputStream.inReader.ReadBytes(len - 4);

                            NetWork.ProcessManager.NetPacketHand.ProcessHand (msgtype, bodydata);
						    
					    }
			
					    inputStream.InputStreamClear ();
				    }
				}
			}
		}

		//关闭Socket
		public static void closeSocket ()
		{
			Debug.Log ("Socket Closed");
			mSocket.Close ();
		} 
		public static void closeSocketLong ()
		{
			Debug.Log ("Socket Closed");
			mSocketLong.Close ();
		}

		public static string GetMacAddress()  
		{  
			string physicalAddress = "";  
			
			NetworkInterface[] nice = NetworkInterface.GetAllNetworkInterfaces();  
			
			foreach (NetworkInterface adaper in nice)  
			{  
				
				Debug.Log(adaper.Description);  
				
				if (adaper.Description == "en0")  
				{  
					physicalAddress = adaper.GetPhysicalAddress().ToString();  
					break;  
				}  
				else  
				{
					physicalAddress = adaper.GetPhysicalAddress().ToString();  
					
					if (physicalAddress != "")  
					{  
						break;  
					};  
				}
			} 
			physicalAddress = SystemInfo.deviceUniqueIdentifier ;
			
			return physicalAddress;  
		} 
	}   
}   


//namespace NetWork.ProcessManager
//{
//	using UnityEngine;   
//	using System.IO;
//
//	public class Service
//	{
//		public static void ProcessHand(BinaryReader sonInReader)
//		{
//			int PacketType = NetWork.NetIoStream.InputStream.readInt (sonInReader);
//			Debug.Log ("packet ID:"+PacketType);
//			switch(PacketType)
//			{
//			case 0:
//				//login
//				LoginHand( sonInReader);
//				break;
//			case 1:
//				ShopHand( sonInReader );
//				break;
//			
//			}
//
//
//		}
//
//
//		public static void LoginHand(BinaryReader sonInReader)
//		{
//			int PacketSonType = NetWork.NetIoStream.InputStream.readInt ( sonInReader);
//			Debug.Log ("packet Son ID:"+PacketSonType);
//			switch (PacketSonType) {
//			case 0:
//				NetWork.PacketProcess.PacketLogin.ReceiveLogin(sonInReader);
//				break;
//			case 1:
//				NetWork.PacketProcess.PacketLogin.ReceiveQuickLogin(sonInReader);
//				break;
//			case 2:
//				NetWork.PacketProcess.PacketLogin.ReceiveRegister(sonInReader);
//				break;
//			}
//		}
//
//		public static void ShopHand(BinaryReader sonInReader)
//		{
//			int PacketSonType = NetWork.NetIoStream.InputStream.readInt ( sonInReader);
//			Debug.Log ("packet Son ID:"+PacketSonType);
//			switch (PacketSonType) {
//			case 0:
//				NetWork.PacketProcess.PacketShop.ReceiveShopList(sonInReader);
//				break;
//			case 1:
//
//				break;
//			case 2:
//
//				break;
//			}
//		}
//	}
//}

namespace NetWork.NetIoStream
{
	using UnityEngine;  
	using System.Collections;  
	using System;
	using System.Threading;
	using System.Net;
	using System.Net.Sockets;
	using System.IO;


	public class OutputStream
	{
		public static MemoryStream memStream = new MemoryStream ();

        public static void writeLen()
        {
            //memStream.Position[0] = 1;
        }

		public static void writeInt(int n)
		{
			memStream.Write (NetWork.Type.TypeConvert.getBytes(n,false), 0, 4);
		}

		public static void writeIntToJava(int n)
		{
			memStream.Write (NetWork.Type.TypeConvert.getBytes(n,true), 0, 4);
		}

		public static void writeShortTojava(short n)
		{
			memStream.Write (NetWork.Type.TypeConvert.getBytes (n, true),0,2);
		}
		public static void writeShort(short n)
		{
			memStream.Write (NetWork.Type.TypeConvert.getBytes (n, false),0,2);
		}

		public static void write( byte[] buffer )
		{
			memStream.Write (buffer, 0, buffer.Length);		
		}

		public static void writeUtf(String msg)
		{
			byte[] msgData = System.Text.Encoding.Default.GetBytes (msg);
//			 = msg.ToCharArray ();
			int len = msgData.Length;
			writeInt (len);
			write (msgData);
		}

		public static void writeUtfTojava(String msg)
		{
			byte[] msgData = System.Text.Encoding.Default.GetBytes (msg);
			//			 = msg.ToCharArray ();
			int len = msgData.Length;
			writeIntToJava (len);
			write (msgData);
		}

		public static void OutputStreamClear()
		{
			memStream = new MemoryStream ();
		}
	}



	public class InputStream
	{
		public static MemoryStream inStream ;
		public static BinaryReader inReader;

//		public static int m_nOffset;

		public InputStream()
		{

		}

		public static void load(byte[] buffer)
		{
			inStream = new MemoryStream (buffer);
			inReader = new BinaryReader(inStream);  

		}

		public static int readInt()
		{
			byte[] m_bIntByte =inReader.ReadBytes (4);

			int m_nInt = NetWork.Type.TypeConvert.getInt (m_bIntByte, true);

			return m_nInt;
		}

		public static int readInt(BinaryReader m_pInReader)
		{
			byte[] m_bIntByte =m_pInReader.ReadBytes (4);
			
			int m_nInt = NetWork.Type.TypeConvert.getInt (m_bIntByte, true);
			
			return m_nInt;
		}

		public static String readUtf()
		{
			byte[] m_bIntByte =inReader.ReadBytes (4);
			int m_nInt = NetWork.Type.TypeConvert.getInt (m_bIntByte, true);

			byte[] strByte = inReader.ReadBytes (m_nInt);
//			string str = System.Text.Encoding.Default.GetString ( strByte );
			string str = System.Text.Encoding.UTF8.GetString ( strByte );

			return str;
		}

		public static String readUtf(BinaryReader m_pInReader)
		{
			byte[] m_bIntByte =m_pInReader.ReadBytes (4);
			int m_nInt = NetWork.Type.TypeConvert.getInt (m_bIntByte, true);
			
			byte[] strByte = m_pInReader.ReadBytes (m_nInt);
//			string str = System.Text.Encoding.Default.GetString ( strByte );
			string str = System.Text.Encoding.UTF8.GetString ( strByte );
			
			return str;
		}


		public static void InputStreamClear()
		{
			if (inReader != null) {
				inReader.Close();
			}
			if (inStream != null) {
				inStream.Close();			
			}
			inStream = null;
			inReader = null;  
		}

	}

	public class InputStreamTwo
	{
		public  MemoryStream inStream ;
		public  BinaryReader inReader;
		
		//		public static int m_nOffset;
		
		public InputStreamTwo()
		{
			
		}
		
		public  void load(byte[] buffer)
		{
			inStream = new MemoryStream (buffer);
			inReader = new BinaryReader(inStream);  
			
		}
		
		public  int readInt()
		{
			byte[] m_bIntByte =inReader.ReadBytes (4);
			
			int m_nInt = NetWork.Type.TypeConvert.getInt (m_bIntByte, true);
			
			return m_nInt;
		}

        public short readShort()
        {
            byte[] m_bIntByte = inReader.ReadBytes(2);

            short m_nInt = NetWork.Type.TypeConvert.getShort(m_bIntByte, false);

            return m_nInt;
        }

        public  int readInt(BinaryReader m_pInReader)
		{
			byte[] m_bIntByte =m_pInReader.ReadBytes (4);
			
			int m_nInt = NetWork.Type.TypeConvert.getInt (m_bIntByte, true);
			
			return m_nInt;
		}
		
		public  String readUtf()
		{
			byte[] m_bIntByte =inReader.ReadBytes (4);
			int m_nInt = NetWork.Type.TypeConvert.getInt (m_bIntByte, true);
			
			byte[] strByte = inReader.ReadBytes (m_nInt);
			//			string str = System.Text.Encoding.Default.GetString ( strByte );
			string str = System.Text.Encoding.UTF8.GetString ( strByte );
			
			return str;
		}
		
		public  String readUtf(BinaryReader m_pInReader)
		{
			byte[] m_bIntByte =m_pInReader.ReadBytes (4);
			int m_nInt = NetWork.Type.TypeConvert.getInt (m_bIntByte, true);
			
			byte[] strByte = m_pInReader.ReadBytes (m_nInt);
			//			string str = System.Text.Encoding.Default.GetString ( strByte );
			string str = System.Text.Encoding.UTF8.GetString ( strByte );
			
			return str;
		}
		
		
		public  void InputStreamClear()
		{
			if (inReader != null) {
				inReader.Close();
			}
			if (inStream != null) {
				inStream.Close();			
			}
			inStream = null;
			inReader = null;  
		}
		
	}
}


namespace NetWork.Type  
{  
	using UnityEngine;  
	using System.Collections;  
	
	public class TypeConvert   
	{  
		
		public TypeConvert()  
		{  
		}  
		
		public  static byte[] getBytes(short s, bool asc)  
		{  
			byte[] buf = new byte[2];  
			if (asc)   
			{  
				for (int i = buf.Length - 1; i >= 0; i--)   
				{          
					buf[i] = (byte) (s & 0x00ff);  
					s >>= 8;  
				}  
			}  
			else  
			{    
				for (int i = 0; i < buf.Length; i++)   
				{  
					
					buf[i] = (byte) (s & 0x00ff);  
					s >>= 8;  
				}  
			}  
			return buf;  
		}  
		public static byte[] getBytes(int s, bool asc)   
		{  
			byte[] buf = new byte[4];  
			if (asc)  
				for (int i = buf.Length - 1; i >= 0; i--)   
			{  
				buf[i] = (byte) (s & 0x000000ff);  
				s >>= 8;  
			}  
			else  
				for (int i = 0; i < buf.Length; i++)   
			{  
				buf[i] = (byte) (s & 0x000000ff);  
				s >>= 8;  
			}  
			return buf;  
		}  
		
		public static byte[] getBytes(long s, bool asc)   
		{  
			byte[] buf = new byte[8];  
			if (asc)  
				for (int i = buf.Length - 1; i >= 0; i--)   
			{  
				buf[i] = (byte) (s & 0x00000000000000ff);  
				s >>= 8;  
			}  
			else  
				for (int i = 0; i < buf.Length; i++)   
			{  
				buf[i] = (byte) (s & 0x00000000000000ff);  
				s >>= 8;  
			}  
			return buf;  
		}  
		public  static short getShort(byte[] buf, bool asc) {  
			if (buf == null) {  
				//throw new IllegalArgumentException("byte array is null!");  
			}  
			if (buf.Length > 2) {  
				//throw new IllegalArgumentException("byte array size > 2 !");  
			}  
			short r = 0;  
			if (asc)  
			for (int i = buf.Length - 1; i >= 0; i--) {  
				r <<= 8;  
				r |= (short)(buf[i] & 0x00ff);  
			}  
			else  
			for (int i = 0; i < buf.Length; i++) {  
				r <<= 8;  
				r |= (short)(buf[i] & 0x00ff);  
			}  
			return r;  
		}  
		public  static int getInt(byte[] buf, bool asc) {  
			if (buf == null) {  
				// throw new IllegalArgumentException("byte array is null!");  
			}  
			if (buf.Length > 4) {  
				//throw new IllegalArgumentException("byte array size > 4 !");  
			}  
			int r = 0;  
			if (asc)  
			for (int i = buf.Length - 1; i >= 0; i--) {  
				r <<= 8;  
				r |= (buf[i] & 0x000000ff);  
			}  
			else  
			for (int i = 0; i < buf.Length; i++) {  
				r <<= 8;  
				r |= (buf[i] & 0x000000ff);  
			}  
			return r;  
		}  
		public static long getLong(byte[] buf, bool asc) {  
			if (buf == null) {  
				//throw new IllegalArgumentException("byte array is null!");  
			}  
			if (buf.Length > 8) {  
				//throw new IllegalArgumentException("byte array size > 8 !");  
			}  
			long r = 0;  
			if (asc)  
			for (int i = buf.Length - 1; i >= 0; i--) {  
				r <<= 8;  
				r |= (buf[i] & 0x00000000000000ff);  
			}  
			else  
			for (int i = 0; i < buf.Length; i++) {  
				r <<= 8;  
				r |= (buf[i] & 0x00000000000000ff);  
			}  
			return r;  
		}  
	}  
}  

