using UnityEngine;
using System.Collections;



public class rn : MonoBehaviour {

	// Use this for initialization
	void Start () {
		//BufferConnection bc = new BufferConnection ();
		//bc.startConnect ();
		//bc.send ("sdsfdf");
		NetWork.Net.SocketManager.SocketConnectionLong ("127.0.0.1", 8888);

		//NetWork.PacketProcess.PacketLogin.sendLogin ("abc","123");
        NetWork.PacketProcess.PacketLogin.sendRegister("abc", "123");
    }
	
	// Update is called once per frame
	void Update () {
	
	}

	void OnDestroy()
	{
		Debug.Log ("close connection");

		NetWork.Net.SocketManager.CloseConnectionLong();
	}

    public void click()
    { 
    
    }
}
