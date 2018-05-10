package com.core.db.redismanager;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;

import com.core.db.RedisManager;
import com.core.db.RedisPlayer;
import com.log.Loggers;

public class RedisPlayerManager {

	private static RedisPlayerManager instance;
	private static final Logger logger = Loggers.gameLogger;
	
	public RedisPlayerManager()
	{
		
	}
	
	public static RedisPlayerManager getInstance()
	{
		if( instance == null )
			instance = new RedisPlayerManager();
		return instance;
	}
	
	
	
	/**
	 * 添加player数据
	 * key		"player-"+playerid  			例：player-1000
	 * value	map<player变量名，对应变量的值>
	 * @param RedisPlayer
	 */
	public void AddPlayerData(RedisPlayer player)
	{
		Map playerMap = RedisManager.getInstance().getClassValueMap(player);
		
        RedisManager.getInstance().getJedis().hmset("player-"+player.getPlayerid(),playerMap);
	}
	
	
	public RedisPlayer getPlayerData(String playerid)
	{
		long ctime = System.currentTimeMillis();
		//List<String> rsmap = jedis.hmget("player-"+playerid, "name");
		Map<String, String> pmap= RedisManager.getInstance().getJedis().hgetAll("player-"+playerid);
		
		logger.info("======================获取时间："+(System.currentTimeMillis()-ctime)+"ms");
		
		RedisPlayer rp = null;
		try {
			rp = RedisManager.getInstance().convertMap(RedisPlayer.class, pmap);
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
		
		return rp;
	}
	
	public void removePlayerData( String playerid )
	{
		long status= RedisManager.getInstance().getJedis().del("player-"+playerid);
		System.out.println("删除"+playerid+":"+status);
	}
}
