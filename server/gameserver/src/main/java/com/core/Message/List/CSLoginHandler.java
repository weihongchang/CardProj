package com.core.Message.List;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.java_websocket.WebSocket;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.core.WsPool;
import com.core.Exception.MessageParseException;
import com.core.Message.CGMessage;
import com.core.Message.Model.Message;
import com.core.Message.Model.Message.CSLogin;
import com.core.db.MongoManager;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.player.Player;
import com.player.PlayerManager;

import io.netty.buffer.ByteBuf;

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

    public void execute(Player player) {
        System.out.println("loginhandler");
        String userName = ((CSLogin)data).getLoginUserName();
        
        Message.SCLogin.Builder msg = Message.SCLogin.newBuilder();
        if( userName == null || userName.isEmpty() )
        {
        	msg.setLoginStatus(-1);
        	msg.setExp(player.getExp());
	        msg.setGold(player.getGold());
	        msg.setLevel(player.getLevel());
	        msg.setMoney(player.getMoney());
	        msg.setName(player.getName());
	        msg.setPlayerid(player.getPlayerid()+"");
             
        	WsPool.sendMessageToUser(conn, msg.build());
        	return ;
        }
        
        Query query=new Query(Criteria.where("account").is(userName));
        List<Player> accountList =  MongoManager.getInstance().getMongoTemplate().find(query,Player.class);
        if( accountList!= null && accountList.size() > 0 )
        {
        	//已有账号
        	Player pl = accountList.get(0);
        	if( pl != null )
        	{
        		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
           	 	pl.setLastLoginTime(df.format(new Date()));   
        		WsPool.updatePlayer(pl, conn);
        		player = pl;
        	}
        }
        else
        {
        	//创建账号
        	 player.setAccount(userName);
        	 player.setName("111");
        	 player.setPlayerid(PlayerManager.getInstance().newPlayerID(1023));
        	 player.setExp(0);
        	 player.setGold(0);
        	 player.setLevel(1);
        	 player.setMoney(0);
        	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        	 player.setCreateTime(df.format(new Date()));
        	 player.setLastLoginTime(df.format(new Date()));   
        	 

        	 MongoManager.getInstance().getMongoTemplate().insert(player);
        }

        msg.setLoginStatus(0);
        msg.setExp(player.getExp());
        msg.setGold(player.getGold());
        msg.setLevel(player.getLevel());
        msg.setMoney(player.getMoney());
        msg.setName(player.getName());
        msg.setPlayerid(player.getPlayerid()+"");
        
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
