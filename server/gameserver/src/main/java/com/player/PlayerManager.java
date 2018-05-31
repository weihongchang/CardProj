package com.player;

import javax.management.InstanceAlreadyExistsException;

import com.util.IdGenerator;

public class PlayerManager {
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
}
