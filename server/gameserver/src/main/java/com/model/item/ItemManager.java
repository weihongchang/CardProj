package com.model.item;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import com.core.db.MongoManager;
import com.model.hero.Hero;
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
	private long maxItemID = 100000;
	
	
/**************************************************************************************/
/**************************************************************************************/
	
	/**
	 * 获取最大物品ID
	 * @return
	 */
	public synchronized long getMaxItemID() {
		return maxItemID++;
	}
	
	
	public void setMaxItemID(long maxItemID) {
		this.maxItemID = maxItemID;
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
			long itemID = getMaxItemID();
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
