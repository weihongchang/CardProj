package com.model.item;

import com.model.BaseObject;

/**
 * 道具
 * @date 	2018年6月6日 下午12:30:46
 * @author 	weihongchang
 *
 */
public class Item extends BaseObject {

	
	/**
	 * 模板id
	 */
	private int templateID;
	
	/**
	 * 道具唯一id
	 */
	private int itemID;
	
	/**
	 * 拥有玩家id
	 */
	private long playerID;
	private String name;
	private int level;
	private int color;
	
	public Item(long playerID,int templateID,int itemID)
	{
		this.playerID = playerID;
		this.templateID = templateID;
		this.itemID = itemID;
		this.level = 1;
		this.color = 0;
	}
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public long getPlayerid() {
		return playerID;
	}
	public void setPlayerid(long playerid) {
		this.playerID = playerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getTemplateID() {
		return templateID;
	}
	public void setTemplateID(int templateID) {
		this.templateID = templateID;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
