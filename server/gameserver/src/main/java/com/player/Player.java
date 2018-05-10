package com.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.IInitializeRequired;
import com.core.GameMessageChannel;
import com.core.IMsg;
import com.core.Message.List.SCAllMessageHandler;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.MessageLite;
import com.start.GameStart;

/**
 * 游戏中的玩家，维护玩家的会话和玩家所有角色的引用
 * 
 * @author 
 *
 */
public class Player implements IInitializeRequired {
	
	Logger logger = LoggerFactory.getLogger("msg");
	

	/** 玩家与GameServer的会话 */
	private GameMessageChannel channel;	
	
	private LoginStateManager _loginStateMang;
	
	
	private int playerid;
	private String name;
	private int exp;
	private int level;
	
	
	public Player()
	{
		
	}
	
	public int getPlayerid() {
		return playerid;
	}

	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Player(GameMessageChannel channel) {
		this.channel = channel;		
		channel.setPlayer(this);
		this._loginStateMang = new LoginStateManager(this);
	}
	
	public void init() {
		
	}
	
	public LoginStateManager getStateManager() {
		return _loginStateMang;
	}

	
	/**
	 * @return
	 */
	public LoginStateEnum getState() {
		return this._loginStateMang.getState();
	}
	
	
	public void sendMessage(IMsg msg)
	{
		if (this.channel != null) {
			channel.write(msg);
			logger.info("[send] " + msg.toString());
			logger.debug("[send] " + msg.toString());
		}
	}
	
	public void sendMessage(GeneratedMessage msgLite)
	{
		SCAllMessageHandler msg = new SCAllMessageHandler(msgLite);
		short mType = GameStart.recognizer.getTypeForMsg(msgLite.getClass());
		msg.setType(mType);
		
		if (this.channel != null) {
			channel.write(msg);
			logger.info("[send] " + msg.toString());
			logger.debug("[send] " + msg.toString());
		}
	}
	
	
	

}
