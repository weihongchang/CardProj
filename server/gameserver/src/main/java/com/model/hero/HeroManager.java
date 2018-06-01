package com.model.hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.model.BaseTemplate;

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

	public List<BaseTemplate> getHeroTemplateList() {
		return heroTemplateList;
	}

	public void setHeroTemplateList(List<BaseTemplate> arr) {
		this.heroTemplateList = arr;
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
			if(heroTemplate != null && heroTemplate.heroid == tID+"" )
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
}
