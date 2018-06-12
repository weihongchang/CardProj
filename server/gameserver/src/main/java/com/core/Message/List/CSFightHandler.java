package com.core.Message.List;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

import org.java_websocket.WebSocket;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.core.Exception.MessageParseException;
import com.core.Message.CGMessage;
import com.core.Message.Model.Message;
import com.core.Message.Model.Message.CSBuyHero;
import com.core.Message.Model.Message.CSFight;
import com.core.db.MongoManager;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.model.combat.CombatManager;
import com.model.hero.HeroManager;
import com.model.hero.HeroTemplate;
import com.model.item.ItemManager;
import com.model.item.ItemTemplate;
import com.model.player.Player;
import com.model.player.PlayerManager;

import io.netty.buffer.ByteBuf;

/**
 * Created by weihongchang
 * 2015/11/24
 */
public class CSFightHandler extends CGMessage {

    private GeneratedMessage data;

    public GeneratedMessage getData() {
        return data;
    }

    public void setData(GeneratedMessage data) {
        this.data = data;
    }

    @Override
    protected boolean readImpl() throws InvalidProtocolBufferException {
    	data = Message.CSFight.parseFrom(readBytes());
		return true;
    }

    public void execute(Player player) {
        System.out.println("csFight");
        
        //1 单绿  ;2 单蓝；3 单紫；4 十绿；5 十蓝；6 十紫；
        int courseid = ((CSFight)data).getCourseID();

        CombatManager.getInstance().FightBegin(player, 1, courseid, courseid);
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
