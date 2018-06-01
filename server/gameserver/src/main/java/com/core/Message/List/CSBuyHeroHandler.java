package com.core.Message.List;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

import org.java_websocket.WebSocket;

import com.core.Exception.MessageParseException;
import com.core.Message.CGMessage;
import com.core.Message.Model.Message;
import com.core.Message.Model.Message.CSBuyHero;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.model.hero.HeroManager;
import com.model.hero.HeroTemplate;
import com.model.player.Player;

import io.netty.buffer.ByteBuf;

/**
 * Created by weihongchang
 * 2015/11/24
 */
public class CSBuyHeroHandler extends CGMessage {

    private GeneratedMessage data;

    public GeneratedMessage getData() {
        return data;
    }

    public void setData(GeneratedMessage data) {
        this.data = data;
    }

    @Override
    protected boolean readImpl() throws InvalidProtocolBufferException {
    	data = Message.CSBuyHero.parseFrom(readBytes());
		return true;
    }

    public void execute(Player player) {
        System.out.println("csbuyhero");
        
        //1 单绿  ;2 单蓝；3 单紫；4 十绿；5 十蓝；6 十紫；
        int bType = ((CSBuyHero)data).getBType();
        
        Message.SCBuyHero.Builder msg = Message.SCBuyHero.newBuilder();
        msg.setStatus(0);
        
        //十连抽
        List<HeroTemplate> hlist =  HeroManager.getInstance().drawHero10();
        if(hlist != null && hlist .size()>0)
        {
        	for (int i = 0; i < hlist.size(); i++) {
        		msg.addHeroid( Integer.parseInt( hlist.get(i).heroid));
			}
        }

        player.sendMessage(msg.build());
        
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