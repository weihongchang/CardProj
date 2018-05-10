package com.core.db.service;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.db.DbManager;
import com.core.db.model.PlayerModel;

public class PlayerDbService {

	private static final Logger log = LoggerFactory.getLogger("msg"); 
	private static PlayerDbService instance;
	
	public static PlayerDbService getInstance()
	{
		if( instance == null )
			instance = new PlayerDbService();
		return instance;
	}
	
	public PlayerDbService()
	{
		
	}
	
	
	/**
	 * ÐÂ½¨ÕËºÅ
	 * @param player
	 */
	public void insertPlayer( PlayerModel player )
	{
		Session session = DbManager.getSession();
		try
		{
			if( session != null )
			{
				Transaction tx = session.beginTransaction();
				session.save(player);
				tx.commit();
			}
		}
		catch(Exception e)
		{
			 e.printStackTrace();
			log.info("insertPlayer error:"+e.getMessage());
		}
		finally
		{
			if( session != null )
				DbManager.CloseSession(session);
		}
	}
	
	public void updatePlayer( long playerid,String name  )
	{
		Session session = DbManager.getSession();
		try
		{
			if( session != null )
			{
				Transaction tx = session.beginTransaction();
				PlayerModel player =(PlayerModel)session.load(PlayerModel.class, playerid);
	            player.setName(name);
	            session.update(player);
	            tx.commit();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("updatePlayer error:"+e.getMessage());
		}
		finally
		{
			if( session != null )
				DbManager.CloseSession(session);
		}
	}
	
}
