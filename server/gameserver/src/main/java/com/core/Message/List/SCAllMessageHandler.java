package com.core.Message.List;

import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;

import com.core.Exception.MessageParseException;
import com.core.Message.CGMessage;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.model.player.Player;

import io.netty.buffer.ByteBuf;

/**
 * Created by weihongchang
 * 2015/11/24
 */
public class SCAllMessageHandler extends CGMessage {

    private GeneratedMessage data;
    
    public SCAllMessageHandler(GeneratedMessage _data)
    {
    	data=_data;
    }

    public GeneratedMessage getData() {
        return data;
    }

    public void setData(GeneratedMessage data) {
        this.data = data;
    }

    @Override
    protected boolean readImpl() throws InvalidProtocolBufferException {
    	
		return false;
    }
    
    @Override
    protected boolean writeImpl() {
    	// TODO Auto-generated method stub
    	writeBytes(data.toByteArray());
    	return true;
    }

    public void execute(Player player) {
      
        
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
