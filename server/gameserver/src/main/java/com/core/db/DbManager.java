package com.core.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbManager {

	protected static final Logger log = LoggerFactory.getLogger("msg");
	private static SessionFactory factorty;
	
	static
	{
		try
		{
			factorty = new Configuration().configure().buildSessionFactory();
		}
		catch(Exception e)
		{
			log.info("create SessionFactiory error:"+e.getMessage());
		}
	}
	
	public static Session getSession()
	{
		return factorty.openSession();
	}
	
	public static void CloseSession(Session session)
	{
		if( session != null )
		{
			if( session.isOpen() )
			{
				session.close();
			}
		}
	}
}
