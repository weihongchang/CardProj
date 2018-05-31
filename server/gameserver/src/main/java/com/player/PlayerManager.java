package com.player;

import javax.management.InstanceAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.util.IdGenerator;

public class PlayerManager {
	private Logger logger = LoggerFactory.getLogger("msg");
	private static PlayerManager instance;
	
	public static PlayerManager getInstance()
	{
		if( instance == null )
		{
			instance = new PlayerManager();
		}
		return instance;
	}
	
	public PlayerManager()
	{
		
	}
	
	/**
	 * 获取用户唯一ID
	 * @return
	 */
	public long newPlayerID(int num)
	{
		IdGenerator ig = new IdGenerator(num);  
		long newID = ig.nextId();
		ig = null;
		return newID;
	}
	
	public Logger getLogger()
	{
		return logger;
	}
}
