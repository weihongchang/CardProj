package com.model.hero;

import com.model.BaseObject;

/**
 * 英雄
 * @date 	2018年6月6日 下午12:30:26
 * @author 	weihongchang
 *
 */
public class Hero extends BaseObject {
	
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
	
	//血量
	private int hp;
	
	private int def;
	
	private int atk;
	
	private int fightAtk;
	private int fightDef;
	
	
	//是否上阵
	private int formationIndex;
	
	public Hero(long playerID,int templateID,int heroID)
	{
		this.playerID = playerID;
		this.templateID = templateID;
		this.heroID = heroID;
		this.level = 1;
		this.exp = 0;
		this.formationIndex = -1;
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

	public int getFormationIndex() {
		return formationIndex;
	}

	public void setFormationIndex(int formationIndex) {
		this.formationIndex = formationIndex;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getFightAtk() {
		return fightAtk;
	}

	public void setFightAtk(int fightAtk) {
		this.fightAtk = fightAtk;
	}

	public int getFightDef() {
		return fightDef;
	}

	public void setFightDef(int fightDef) {
		this.fightDef = fightDef;
	}

}
