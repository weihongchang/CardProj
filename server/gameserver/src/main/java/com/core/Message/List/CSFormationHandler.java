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
import com.core.Message.Model.Message.CSFormation;
import com.core.db.MongoManager;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
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
public class CSFormationHandler extends CGMessage {

    private GeneratedMessage data;

    public GeneratedMessage getData() {
        return data;
    }

    public void setData(GeneratedMessage data) {
        this.data = data;
    }

    @Override
    protected boolean readImpl() throws InvalidProtocolBufferException {
    	data = Message.CSFormation.parseFrom(readBytes());
		return true;
    }

    public void execute(Player player) {
        System.out.println("CSFormation");
        
        int count = ((CSFormation)data).getFormationIDCount();
        
        Message.SCBuyHero.Builder msg = Message.SCBuyHero.newBuilder();
        msg.setStatus(0);
        
        //十连抽
        List<HeroTemplate> hlist =  HeroManager.getInstance().drawHero10();
        if(hlist != null && hlist .size()>0)
        {
        	for (int i = 0; i < hlist.size(); i++) {
        		if(HeroManager.getInstance().createHero(player, hlist.get(i)))
        			msg.addHeroid( Integer.parseInt( hlist.get(i).heroid));
			}
        }
        
//      Query query=new Query(Criteria.where("playerid").is(player.getPlayerid()));
//		Update update = Update.update("heroList", player.getHeroList());
//		MongoManager.getInstance().getMongoTemplate().updateFirst(query, update, Player.class);

        player.sendMessage(msg.build());
        
        PlayerManager.getInstance().sendHeroList(player);
        
        ItemTemplate item1 = ItemManager.getInstance().getItemTemplateForID(270003);
        ItemManager.getInstance().createItem(player, item1);
        
        ItemTemplate item2 = ItemManager.getInstance().getItemTemplateForID(270004);
        ItemManager.getInstance().createItem(player, item2);
        
        PlayerManager.getInstance().sendItemList(player);

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
