package com.model.player;

import java.util.ArrayList;
import java.util.List;

import org.java_websocket.WebSocket;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.core.WsPool;
import com.core.db.MongoManager;
import com.google.protobuf.GeneratedMessage;
import com.model.hero.Hero;
import com.model.item.Item;

/**
 * 游戏中的玩家，维护玩家的会话和玩家所有角色的引用
 * 
 * @author 
 *
 */
@Document(collection="player") 
public class Player  {
	
//	Logger logger = LoggerFactory.getLogger("msg");
	
//
//	/** 玩家与GameServer的会话 */
//	private GameMessageChannel channel;	
//	
//	private LoginStateManager _loginStateMang;
	
	private String account;
	@Indexed
	private long playerid;
	private String name;
	private int exp;
	private int level;
	private int money;
	private int gold;
	private String ip;
	private String createTime;
	private String lastLoginTime;
	
	//英雄列表
	@Transient
	private List<Hero> heroList = new ArrayList<>();
	
	/**
	 * 道具列表
	 */
	@Transient
	private List<Item> itemList = new ArrayList<>();
	
	
	public Player(String ip)
	{
		this.ip = ip;
	}
	
	public long getPlayerid() {
		return playerid;
	}

	public void setPlayerid(long playerid) {
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	
	public List<Hero> getHeroList() {
		return heroList;
	}

	public void setHeroList(List<Hero> heroList) {
		this.heroList = heroList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

/************************************************************************************/
/************************************************************************************/
	/**
	 * 发送消息到客户端
	 * @param msg
	 */
	public void sendMessage(GeneratedMessage msg)
	{
		WebSocket conn = WsPool.getConnByUser(getIp());
		if (conn != null) {
			WsPool.sendMessageToUser(conn, msg);
//			PlayerManager.getInstance().getLogger().info("[send] " + msg.toString());
//			PlayerManager.getInstance().getLogger().debug("[send] " + msg.toString());
		}
	}
	
	/**
	 * 添加一个英雄
	 * @param hero
	 */
	public void AddHero(Hero hero)
	{
		heroList.add(hero);
		MongoManager.getInstance().getMongoTemplate().insert(hero);
	}
	
	/**
	 * 添加一个道具
	 * @param item
	 */
	public void AddItem(Item item)
	{
		itemList.add(item);
		MongoManager.getInstance().getMongoTemplate().insert(item);
	}
	
	/**
	 * 数据库加载Hero
	 */
	public void dbLoadHero()
	{
		 Query query=new Query(Criteria.where("playerID").is(getPlayerid()));
		 setHeroList( MongoManager.getInstance().getMongoTemplate().find(query,Hero.class));
	}
	
	/**
	 * 数据库加载Item
	 */
	public void dbLoadItem()
	{
		 Query query=new Query(Criteria.where("playerID").is(getPlayerid()));
		 setItemList( MongoManager.getInstance().getMongoTemplate().find(query,Item.class));
	}

}
