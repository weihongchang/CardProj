package com.core.db;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;

import com.log.Loggers;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

/**
 * redist 管理
 * @author weihongchang
 * @date 2015年11月16日
 */
public class RedisManager {
	
	private static RedisManager instance;
	private static final Logger logger = Loggers.gameLogger;
	private Jedis jedis;
	public RedisManager()
	{
		jedis = new Jedis("localhost", 6379);
		jedis.connect();
	}
	
	public static RedisManager getInstance()
	{
		if( instance == null )
			instance = new RedisManager();
		return instance ;
	}
	

	public Jedis getJedis()
	{
		return jedis;
	}
	
	
	

//*****************************************************************************************************************
	public void rank()
	{
		//long status = jedis.sort("player-*", "level1");
		SortingParams sortingParameters = new SortingParams();
		sortingParameters.desc();
		sortingParameters.limit(0, 50);
//		sortingParameters.get("playerid*->level");
		//sortingParameters.get("level");
//		sortingParameters.get("player-*");
//		sortingParameters.by("level");
		Set<String> klist = jedis.keys("player-*");
		
		//String mm = jedis.("playeritem-1049").toString();
		Set<String> klist1 = jedis.keys("playeritem-*");
		List<String> plist2 = jedis.sort("player*");
		List<String> plist3 = jedis.sort("playeritem-*",sortingParameters);
		
		
		List<String> plist1 = jedis.sort("player-*");
		List<String> plist = jedis.sort("player*", sortingParameters);
		
		logger.info("排序状态：");
	}
	

//*****************************************************************************************************************	
	/**
	 * 慎重。清空所有数据
	 */
	public void clear()
	{
	    Set keys = jedis.keys("*");   
        Iterator t1=keys.iterator() ;  
        
        while(t1.hasNext()){   
            Object obj1=t1.next();   
            jedis.del(obj1.toString());
//            System.out.println(obj1);   
        } 
	}
	
	public Map getClassValueMap(Object obj)
	{
		Map objMap = new HashMap<String, String>();
		
		Field[] fields = obj.getClass().getDeclaredFields(); 
		for(int i=0;i<fields.length;i++)
		{
			String fieldName = fields[i].getName();
			objMap.put(fieldName, getFieldValueByName(fieldName, obj).toString());
//			System.out.println("变量名："+fieldName+"   值:"+ getFieldValueByName(fieldName, obj).toString());
		}
		return objMap;
	}
	
	private Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("属性不存在");
			return null;
		}
	}
	
	public  <T> T convertMap(Class<T> type, Map<String, String> map)
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		T t = type.newInstance();
		BeanUtils.copyProperties(t, map);
		return t;
	}

//  //删除map中的某个键值  
//  jedis.hdel("user","age");
//  System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null  
//  System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2 
//  System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true  
//  System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  
//  System.out.println(jedis.hvals("user"));//返回map对象中的所有value 
//
//  Iterator<String> iter=jedis.hkeys("user").iterator();  
//  while (iter.hasNext()){  
//      String key = iter.next();  
//      System.out.println(key+":"+jedis.hmget("user",key));  
//  } 
}
