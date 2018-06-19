package com.model.hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import com.core.db.MongoManager;
import com.model.BaseTemplate;
import com.model.item.Item;
import com.model.item.ItemManager;
import com.model.player.Player;

public class HeroManager {
	private static volatile HeroManager sInst = null;
	
	//英雄模板集合
	private List<BaseTemplate> heroTemplateList = null;
	
	private HeroManager() {
		heroTemplateList = new ArrayList<>();
	}

	public static HeroManager getInstance() {
		HeroManager inst = sInst;
		if (inst == null) {
			synchronized (HeroManager.class) {
				inst = sInst;
				if (inst == null) {
					inst = new HeroManager();
					sInst = inst;
				}
			}
		}
		return inst;
	}
	
	/**
	 * 当前英雄最大id
	 */
	private int maxHeroID = 100000;
	
	
/**************************************************************************************/
/**************************************************************************************/
	
	public synchronized int getMaxHeroID() {
		return maxHeroID++;
	}

	public void setMaxHeroID(int maxHeroID) {
		this.maxHeroID = maxHeroID;
	}

	public List<BaseTemplate> getHeroTemplateList() {
		return heroTemplateList;
	}

	public void setHeroTemplateList(List<BaseTemplate> arr) {
		this.heroTemplateList = arr;
	}
	
/*******************************************************************************/
/*******************************************************************************/
	/**
	 * 获取一个新的英雄id
	 * @return
	 */
	public synchronized long newHeroID()
	{
		return	maxHeroID++;
	}
	
	/**
	 * 根据英雄模板id获取模板数据
	 * @param tID
	 * @return
	 */
	public HeroTemplate getHeroTemplateForID(int tID)
	{
		for (int i = 0; i < heroTemplateList.size(); i++) {
			
			HeroTemplate heroTemplate = (HeroTemplate) heroTemplateList.get( i );
			if(heroTemplate != null && heroTemplate.heroid.equals(tID+"" ))
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
	public HeroTemplate getHeroTemplateForIndex(int index)
	{
		if(index >=0 && index < heroTemplateList.size()) 
		{
			HeroTemplate heroTemplate = (HeroTemplate) heroTemplateList.get( index );
			if(heroTemplate != null  )
			{
				return heroTemplate;
			}
		}
		return null;
	}
	
	/**
	 * 十连抽
	 * @return
	 */
	public List<HeroTemplate> drawHero10()
	{
		List<HeroTemplate> hlist = new ArrayList<>(10);
		Random random = new Random();
		List<Integer> indexList = new ArrayList<>();
		while (indexList.size() <10) {
			int hIndex = random.nextInt(heroTemplateList.size());
			if( hIndex >= 0 && hIndex< heroTemplateList.size() )
			{
				indexList.add(hIndex);
			}
		}
		
		for (int i = 0; i < indexList.size(); i++) {
			HeroTemplate h = HeroManager.getInstance().getHeroTemplateForIndex(indexList.get( i ));
			if( h != null )
			{
				hlist.add(h);
			}
		}
		
		return hlist;
	}
	
	/**
	 * 获取的新英雄
	 * @param player
	 * @param template
	 */
	public boolean createHero(Player player,HeroTemplate template)
	{
		if(player != null  && template != null)
		{
			int heroID = getMaxHeroID();
			Hero hero = new Hero(player.getPlayerid(), Integer.parseInt( template.heroid),heroID );
			if( hero != null )
			{
				player.AddHero(hero);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 初始化hero最大id
	 */
	public void dbLoadHeroMaxID()
	{
		Query query=new Query();
		query.with(new Sort(Sort.Direction.DESC,   "heroID"));
		query.limit(1);
		List<Hero> herolist = MongoManager.getInstance().getMongoTemplate().find(query, Hero.class);
		if( herolist != null && herolist.size()>0)
		{
			HeroManager.getInstance().setMaxHeroID(herolist.get(0).getHeroID());
		}
	}
}
