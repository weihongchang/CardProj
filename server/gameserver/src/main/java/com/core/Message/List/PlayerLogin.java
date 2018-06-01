package com.core.Message.List;

import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;

import com.core.Exception.MessageParseException;
import com.core.Message.CGMessage;
import com.core.Message.MessageType;
import com.core.Message.Model.MsgTest;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.model.player.Player;

import io.netty.buffer.ByteBuf;

public class PlayerLogin extends CGMessage {

	private GeneratedMessage data;
	
	public void execute(Player player) {
		// TODO Auto-generated method stub
		
		String account = ((MsgTest.LoginReq)getData()).getEmail();
		String password = ((MsgTest.LoginReq)getData()).getPassword();
		String key = ((MsgTest.LoginReq)getData()).getSessionKey();
		//System.out.println("cgPublicPlayerLogin [" + cgPublicPlayerLogin.getData().toString() + "]");
		
		PlayerLogin pl = new PlayerLogin();
		MsgTest.LoginReq.Builder b= MsgTest.LoginReq.newBuilder();
		b.setEmail("aa@qq.com");
		b.setPassword("99999999");
		b.setSessionKey("00000");
		
		
		pl.setData(b.build());
//		this.getChannel().getPlayer().sendMessage(pl);
	}

	@Override
	protected boolean readImpl() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		data = MsgTest.LoginReq.parseFrom(readBytes());
		return true;
	}
	
	@Override
	public short getType() {
		// TODO Auto-generated method stub
		return MessageType.CG_PUBLIC_PLAYER_LOGIN;
	}

	@Override
	protected boolean writeImpl() {
		// TODO Auto-generated method stub
		writeBytes(data.toByteArray());
		return true;
	}
	
	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "CG_PUBLIC_PLAYER_LOGIN";
	}


	public GeneratedMessage getData(){
		return data;
	}
		
	public void setData(GeneratedMessage data){
		this.data = data;
	}

	public boolean read(ByteBuffer buff) throws MessageParseException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean write(ByteBuffer buff) throws MessageParseException {
		// TODO Auto-generated method stub
		return false;
	}

	public WebSocket getConn() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setConn(WebSocket conn) {
		// TODO Auto-generated method stub
		
	}

	public boolean read(ByteBuf buff) throws MessageParseException {
		// TODO Auto-generated method stub
		return false;
	}

}
