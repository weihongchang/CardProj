package com.model.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.Message.Model.Message;
import com.model.hero.Hero;
import com.model.item.Item;
import com.util.IdGenerator;

public class PlayerManager {
	private Logger logger = LoggerFactory.getLogger("msg");
	
	/**
	 * 拥有英雄个数上限
	 */
	public final int heroMaxCount = 100;
	
	private static volatile PlayerManager sInst = null;

	private PlayerManager() {
	}

	public static PlayerManager getInstance() {
		PlayerManager inst = sInst;
		if (inst == null) {
			synchronized (PlayerManager.class) {
				inst = sInst;
				if (inst == null) {
					inst = new PlayerManager();
					sInst = inst;
				}
			}
		}
		return inst;
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
	
/******************************************************************************************/
/******************************************************************************************/
	/**
	 * 发送英雄列表
	 * @param player
	 */
	public void sendHeroList(Player player)
	{
		Message.SCHeroList.Builder msg = Message.SCHeroList.newBuilder();
		for (int i = 0; i < player.getHeroList().size(); i++) {
			Hero hero = player.getHeroList().get( i );
			if( hero != null )
			{
				Message.CSHero.Builder h = Message.CSHero.newBuilder();
				h.setExp( hero.getExp());
				h.setHeroid(hero.getHeroID());
				h.setTemplateid(hero.getTemplateID());
				h.setLevel(hero.getLevel());
				
				msg.addHeroData(h);
			}
		}
		
		if( msg.getHeroDataCount() >0 )
		{
			player.sendMessage(msg.build());
		}
		
	}
	
	
	/**
	 * 发送道具列表
	 * @param player
	 */
	public void sendItemList(Player player)
	{
		Message.SCItemList.Builder msg = Message.SCItemList.newBuilder();
		for (int i = 0; i < player.getItemList().size(); i++) {
			Item item = player.getItemList().get( i );
			if( item != null )
			{
				Message.CSItem.Builder h = Message.CSItem.newBuilder();
				h.setColor( item.getColor());
				h.setItemid(item.getItemID());
				h.setTemplateid(item.getTemplateID());
				h.setLevel(item.getLevel());
				
				msg.addItemData(h);
			}
		}
		
		if( msg.getItemDataCount() >0 )
		{
			player.sendMessage(msg.build());
		}
		
	}
}
