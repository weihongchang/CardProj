package com.core.db.redismanager;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;

import com.core.db.RedisManager;
import com.log.Loggers;
import com.model.item.Item;

public class RedisItemManager {

	private static RedisItemManager instance;
	private static final Logger logger = Loggers.gameLogger;
	
	public RedisItemManager()
	{
		
	}
	
	public static RedisItemManager getInstance()
	{
		if( instance == null )
			instance = new RedisItemManager();
		return instance;
	}
	
	/**
	 * 添加Item数据
	 * 生成 和playerid的映射表   "playeritem-"+playerid   例:playeritem-1000
	 * 生成 item属性   key{ "item-"+itemid} value{ map<Item变量名，对应变量的值> }
	 * @param item
	 */
	public void AddItemData(Item item)
	{
		int playerid = item.getPlayerid();
		long itemid = item.getItemID();
		RedisManager.getInstance().getJedis().lpush("playeritem-"+playerid, itemid+"");
		
		Map itemMap = RedisManager.getInstance().getClassValueMap(item);
		RedisManager.getInstance().getJedis().hmset("item-"+item.getItemID(),itemMap);
	}
	
	/**
	 * 根据itemid获取item
	 * @param itemid
	 * @return
	 */
	public Item getItemData(long itemid)
	{
		//jedis.lrange("stringlists", 0, -1);
		long ctime = System.currentTimeMillis();
		//List<String> rsmap = jedis.hmget("player-"+playerid, "name");
		Map<String, String> pmap= RedisManager.getInstance().getJedis().hgetAll("item-"+itemid);
		Item item = null;
		try {
			item = RedisManager.getInstance().convertMap(Item.class, pmap);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
	
}
