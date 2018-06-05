package com.model.hero;

import org.springframework.data.mongodb.core.mapping.Document;

public class Hero {
	
	//模板id
	private int templateID;
	
	//唯一id
	private int heroID;
	
	//拥有着id
	private long playerID;
	
	//等级
	private int level;
	
	//经验
	private int exp;
	
	public Hero(long playerID,int templateID,int heroID)
	{
		this.playerID = playerID;
		this.templateID = templateID;
		this.heroID = heroID;
		this.level = 1;
		this.exp = 0;
	}

	public int getTemplateID() {
		return templateID;
	}

	public void setTemplateID(int templateID) {
		this.templateID = templateID;
	}

	public int getHeroID() {
		return heroID;
	}

	public void setHeroID(int heroID) {
		this.heroID = heroID;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public long getPlayerID() {
		return playerID;
	}

	public void setPlayerID(long playerID) {
		this.playerID = playerID;
	}
	
	
	
}
