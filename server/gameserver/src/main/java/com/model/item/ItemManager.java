package com.model.item;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import com.core.db.MongoManager;
import com.model.BaseTemplate;
import com.model.hero.HeroTemplate;
import com.model.player.Player;

public class ItemManager {
	private static volatile ItemManager sInst = null;
	
	private ItemManager() {
	}
	
	public static ItemManager getInstance() {
		ItemManager inst = sInst;
		if (inst == null) {
			synchronized (ItemManager.class) {
				inst = sInst;
				if (inst == null) {
					inst = new ItemManager();
					sInst = inst;
				}
			}
		}
		return inst;
	}
	
	/**
	 * 当前英雄最大id
	 */
	private int maxItemID = 100000;
	
	//道具模板集合
	private List<BaseTemplate> itemTemplateList = null;
	
	
/**************************************************************************************/
/**************************************************************************************/
	
	/**
	 * 获取最大物品ID
	 * @return
	 */
	public synchronized int getMaxItemID() {
		return maxItemID++;
	}
	
	
	public void setMaxItemID(int maxItemID) {
		this.maxItemID = maxItemID;
	}

	public List<BaseTemplate> getImteTemplateList() {
		return itemTemplateList;
	}

	public void setImteTemplateList(List<BaseTemplate> imteTemplateList) {
		itemTemplateList = imteTemplateList;
	}
	
	
	/**
	 * 根据道具模板id获取模板数据
	 * @param tID
	 * @return
	 */
	public ItemTemplate getItemTemplateForID(int tID)
	{
		for (int i = 0; i < itemTemplateList.size(); i++) {
			ItemTemplate heroTemplate = (ItemTemplate) itemTemplateList.get( i );
			if(heroTemplate != null && heroTemplate.itemid.equals( tID+"" ))
			{
				return heroTemplate;
			}
		}
		return null;
	}
	
	/**
	 * 根据index获取hero模板
	 * @param index
	 * @return
	 */
	public ItemTemplate getItemTemplateForIndex(int index)
	{
		if(index >=0 && index < itemTemplateList.size()) 
		{
			ItemTemplate heroTemplate = (ItemTemplate) itemTemplateList.get( index );
			if(heroTemplate != null  )
			{
				return heroTemplate;
			}
		}
		return null;
	}

	/**
	 * 获取的新道具
	 * @param player
	 * @param template
	 */
	public boolean createItem(Player player,ItemTemplate template)
	{
		if(player != null  && template != null)
		{
			int itemID = getMaxItemID();
			Item item = new Item(player.getPlayerid(), Integer.parseInt( template.itemid),itemID );
			if( item != null )
			{
				player.AddItem(item);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 初始化item最大id
	 */
	public void dbLoadItemMaxID()
	{
		Query query=new Query();
		query.with(new Sort(Sort.Direction.DESC,   "itemID"));
		query.limit(1);
		List<Item> itemlist = MongoManager.getInstance().getMongoTemplate().find(query, Item.class);
		if( itemlist != null && itemlist.size()>0)
		{
			ItemManager.getInstance().setMaxItemID(itemlist.get(0).getItemID());
		}
	}
}
