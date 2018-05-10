package com.core.db;

import com.core.db.redismanager.RedisItemManager;
import com.core.db.redismanager.RedisPlayerManager;
import com.item.Item;

public class DbRedisManager {

	public static void main(String[] args)
	{
		
		RedisManager.getInstance().rank();  
//		addTestItemData();
		
//		int cid = 100;
//		RedisPlayer rp = new RedisPlayer();	
//		rp.setPlayerid(cid);
//		rp.setExp(100);
//		rp.setLevel(10);
//		rp.setName("test"+cid);
//		RedisManager.getInstatnce().AddPlayerData(rp);
		
		//RedisManager.getInstatnce().getPlayerData(100+"");
		
		
//		int pid=5000;
//		Random rand = new Random();
//		for( int i=0;i<100;i++ )
//		{
//			int id = pid+rand.nextInt(100000);
//			RedisManager.getInstatnce().getPlayerData(id+"");
//		}
		
		//RedisManager.getInstatnce().removePlayerData("1000");
		//RedisManager.getInstatnce().removePlayerData("10001");
		
//		new RedisClient().show(); 
		
//		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.connect();
//		if(jedis.isConnected())
//		{
//			System.out.println(jedis.isConnected());
//			System.out.println("redis connection!");
//			jedis.set("id", "123456");
//			System.out.println(jedis.isConnected());
//		}
//		else
//		{
//			System.out.println("redis connection error!");
//		}
	}
	
	public static void addTestData()
	{
		int testid=1000;
		for( int i=0;i<100;i++ )
		{
			int cid = testid+i;
			RedisPlayer rp = new RedisPlayer();	
			rp.setPlayerid(cid);
			rp.setExp(100);
			rp.setLevel(i+1);
			rp.setName("test"+cid);
			RedisPlayerManager.getInstance().AddPlayerData(rp);
		}
	}
	
	public static void addTestItemData()
	{
		int testid=1000;
		for( int i=0;i<100;i++ )
		{
			int cid = testid+i;
			Item rp = new Item();	
			rp.setPlayerid(cid);
			rp.setItemID(cid);
			rp.setLevel(i+1);
			rp.setName("item"+cid);
			rp.setColor(1);
			
			RedisItemManager.getInstance().AddItemData(rp);
		}
	}
	

}
