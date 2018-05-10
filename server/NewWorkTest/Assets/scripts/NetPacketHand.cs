namespace NetWork.ProcessManager
{
	using UnityEngine;   
	using System.IO;
    using Google.ProtocolBuffers;
	
	public class NetPacketHand
	{
		private static  int packetIdSelfGrowthFactor = 0;
		public const int CS_LOGIN = 101; 
        public const int SC_LOGIN = 102; 
        public const int CS_REGISTER = 103;
        public const int SC_REGISTER = 104;

        public static void ProcessHand(int msgType, byte[] bodydata)
		{
			switch(msgType)
			{
			case SC_LOGIN:
                    //login
                    LoginHandler(bodydata);
				break;

			case SC_REGISTER:
                    RegisterHandler(bodydata);
                    break;
			case 13:
				//ShopHand( sonInReader );
				break;
			}
		}
		
		
		public static void LoginHandler(byte[] bodydata)//Google.ProtocolBuffers.IMessageLite msg)
		{
            // LoginResp lrp = (LoginResp)msg;
            // Debug.Log("return value:" + lrp.Status);
            com.core.Message.Model.SCLogin lr = com.core.Message.Model.SCLogin.ParseFrom(bodydata);

            //LoginReq lr = (LoginReq)msg;

            // LoginResp lrp = LoginResp.ParseFrom(bodydata);
            if (lr != null)
            {
                // Debug.Log("return value:"+lrp.Status);
                Debug.Log("return value:" + lr.LoginStatus);
               // Debug.Log("return value:" + lr.Password);
                //Debug.Log("return value:" + lr.SessionKey);
            }
        }

        public static void RegisterHandler(byte[] bodydata)
        {
            Debug.Log("RegisterHandler return value:");
            com.core.Message.Model.SCRegister msg = com.core.Message.Model.SCRegister.ParseFrom(bodydata);

            if (msg != null)
            {
                Debug.Log("return value:" + msg.Status);
            }
        }

        public static void FightHand(BinaryReader sonInReader)
		{

		}
		
		public static void ShopHand(BinaryReader sonInReader)
		{
			
		}
	}
}