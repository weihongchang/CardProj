package com.model;

import com.core.db.MongoManager;

public class BaseObject {

	public void dbUpdate()
	{
		MongoManager.getInstance().getMongoTemplate().save(this);
	}
}
