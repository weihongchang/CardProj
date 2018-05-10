package com.core.Message.List;

import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;

import com.core.IMsg;
import com.core.WsPool;
import com.core.Exception.MessageParseException;
import com.core.Message.CGMessage;
import com.core.Message.Model.Message;
import com.core.Message.Model.Message.SCMsg;
import com.core.Message.Model.MsgTest;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.sctp.SctpMessageCompletionHandler;

/**
 * Created by weihongchang
 * 2015/11/24
 */
public class CSLoginHandler extends CGMessage {

    private GeneratedMessage data;

    public GeneratedMessage getData() {
        return data;
    }

    public void setData(GeneratedMessage data) {
        this.data = data;
    }

    @Override
    protected boolean readImpl() throws InvalidProtocolBufferException {
    	data = Message.CSLogin.parseFrom(readBytes());
		return true;
    }

    public void execute() {
        System.out.println("loginhandler");
        
//        Message.SCMsg.Builder msg = Message.SCMsg.newBuilder();
//        msg.setUsername("h接口数量的减肥速度");
        MsgTest.LoginResp.Builder msg = MsgTest.LoginResp.newBuilder();
        
        msg.setStatus(100);
        msg.setDes("ddwe");
//        this.getChannel().getPlayer().sendMessage(msg.build());
        WsPool.sendMessageToUser(conn, msg.build());
        
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
