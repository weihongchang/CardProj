
namespace NetWork.PacketProcess
{
	using UnityEngine;
	using System.Collections;
	using System.IO;
    using Google.ProtocolBuffers;
    
    
	public class PacketLogin
	{
		private static int  cspt_login_selfGrowthFactor = 0;
		public  static int CSPT_Login = cspt_login_selfGrowthFactor++; 
		public  static int CSPT_QuickRegister = cspt_login_selfGrowthFactor++;
		public  static int CSPT_Register = cspt_login_selfGrowthFactor++; 
		//login
		public static void sendLogin(string username,string pwd)
		{
			short len = 0;
            com.core.Message.Model.CSLogin.Builder loginBuild = com.core.Message.Model.CSLogin.CreateBuilder();
            loginBuild.SetLoginPassWord("123456");
            loginBuild.SetLoginUserName("abcd");
            loginBuild.SetSessionKey("789456");
            loginBuild.SetWorldid(1);

            MemoryStream ms = new MemoryStream();

            //string ll = lr.ToByteString().ToStringUtf8();

            byte[] bdata = loginBuild.Build().ToByteArray();
            

            len = (short)(bdata.Length + 4);

            NetWork.NetIoStream.OutputStream.writeShortTojava(len);
            NetWork.NetIoStream.OutputStream.writeShortTojava(101);
            NetWork.NetIoStream.OutputStream.write(bdata);

            NetWork.Net.SocketManager.sendDataLong ();
		}



		public static void sendRegister(string username,string pwd)
		{
            short len = 0;
            com.core.Message.Model.CSRegister.Builder loginBuild = com.core.Message.Model.CSRegister.CreateBuilder();
            loginBuild.SetRegisterUserName("222222");
            loginBuild.SetRegisterPassword("uuuuuu");
            

            MemoryStream ms = new MemoryStream();

            byte[] bdata = loginBuild.Build().ToByteArray();


            len = (short)(bdata.Length + 4);

            NetWork.NetIoStream.OutputStream.writeShortTojava(len);
            NetWork.NetIoStream.OutputStream.writeShortTojava(103);
            NetWork.NetIoStream.OutputStream.write(bdata);

            NetWork.Net.SocketManager.sendDataLong();
        }


		public static void sendEmail(int test)
		{
			
			//int packetLength
			NetWork.NetIoStream.OutputStream.writeInt(12);
            //int packetType
            NetWork.NetIoStream.OutputStream.writeInt(2);// NetWork.ProcessManager.NetPacketHand.CPT_EMAIL);
			//int packdtSonType
			NetWork.NetIoStream.OutputStream.writeInt(0);
			
			NetWork.NetIoStream.OutputStream.writeInt (10);
			NetWork.Net.SocketManager.sendDataLong ();
		}

		public static void sendEmail2(int test)
		{
			
			//int packetLength
			NetWork.NetIoStream.OutputStream.writeInt(12);
            //int packetType
            NetWork.NetIoStream.OutputStream.writeInt(1);//  NetWork.ProcessManager.NetPacketHand.CPT_EMAIL);
			//int packdtSonType
			NetWork.NetIoStream.OutputStream.writeInt(1);
			
			
			NetWork.NetIoStream.OutputStream.writeInt (10);
			NetWork.Net.SocketManager.sendDataLong ();
		}

		/// <summary>//////////////////////////////////////////////////////////////////
		/// 接收指令////////////////////////////////////////////////////////////////////
		/// </summary>////////////////////////////////////////////////////////////////

		public static void ReceiveLogin(BinaryReader InReader)
		{
			//monster test
			int retValue = NetWork.NetIoStream.InputStream.readInt (InReader );
			string accountid = NetWork.NetIoStream.InputStream.readUtf (InReader );

		}


		//快速注册
		public static void ReceiveQuickLogin(BinaryReader InReader)
		{
			int retValue = NetWork.NetIoStream.InputStream.readInt(InReader );
			string accountid = NetWork.NetIoStream.InputStream.readUtf (InReader );
			string username = NetWork.NetIoStream.InputStream.readUtf (InReader);
			string pwd = NetWork.NetIoStream.InputStream.readUtf (InReader);

						Debug.Log ("return value:"+accountid);
						Debug.Log ("username:"+username);
						Debug.Log ("password:"+pwd);
			
			if (retValue == 0) 
			{
				//to main scene

				//GameMain.getInstance().getPlayer().setAccountid( accountid );
				
			} else {
				//quick register error
				
			}

		}

		//注册
		public static void ReceiveRegister(BinaryReader InReader)
		{
			int retValue = NetWork.NetIoStream.InputStream.readInt(InReader );
			string accountid = NetWork.NetIoStream.InputStream.readUtf (InReader );
			string username = NetWork.NetIoStream.InputStream.readUtf (InReader);
			string pwd = NetWork.NetIoStream.InputStream.readUtf (InReader);

		}


	
	}
}
