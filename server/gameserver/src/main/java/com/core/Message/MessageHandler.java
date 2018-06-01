package com.core.Message;



import com.core.Message.List.PlayerLogin;
import com.core.Message.Model.MsgTest;
import com.model.player.Player;

public class MessageHandler {
	private static MessageHandler instance ;
	
	public MessageHandler()
	{}
	
	public static MessageHandler getInstance()
	{
		if( instance == null )
			instance = new MessageHandler();
		return instance ;
	}
	
	
	public void PlayerLogin (Player player, PlayerLogin cgPublicPlayerLogin)
	{
		
		String account = ((MsgTest.LoginReq)cgPublicPlayerLogin.getData()).getEmail();
		String password = ((MsgTest.LoginReq)cgPublicPlayerLogin.getData()).getPassword();
		String key = ((MsgTest.LoginReq)cgPublicPlayerLogin.getData()).getSessionKey();
		//System.out.println("cgPublicPlayerLogin [" + cgPublicPlayerLogin.getData().toString() + "]");
		
		PlayerLogin pl = new PlayerLogin();
//		MsgTest.LoginResp.Builder  b = MsgTest.LoginResp.newBuilder();
//		pl.setMsgType( );
		MsgTest.LoginReq.Builder b= MsgTest.LoginReq.newBuilder();
		b.setEmail("aa@qq.com");
		b.setPassword("99999999");
		b.setSessionKey("00000");
		
		
		pl.setData(b.build());
//		player.sendMessage(pl);
		
//		GCRoleList gcRoleList = new GCRoleList();
//		PROTO_GC_RoleList.Builder roleList = PROTO_GC_RoleList.newBuilder();
//		roleList.setRoleState(1);
//		gcRoleList.setData(roleList.build());
//		player.sendMessage(gcRoleList);
//		
//		GCSystemMessage gcSystemMessage = new GCSystemMessage();
//		PROTO_GC_SystemMessage.Builder sysMsg = PROTO_GC_SystemMessage.newBuilder();
//		sysMsg.setContent("sucess").setColor(1).setShowType(1);
//		
//		gcSystemMessage.setData(sysMsg.build());
//		
//		player.sendMessage(gcSystemMessage);
	}
}
