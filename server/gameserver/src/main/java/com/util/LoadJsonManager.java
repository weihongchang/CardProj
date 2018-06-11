package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.model.BaseTemplate;
import com.model.dungeon.DungeonManager;
import com.model.formation.FormationManager;
import com.model.hero.HeroManager;
import com.model.item.ItemManager;

public class LoadJsonManager {
	private static LoadJsonManager instance;
	 
	public static LoadJsonManager getInstance()
	{
		if(instance == null)
		{
			instance = new LoadJsonManager();
		}
		return instance;
	}
	
	public LoadJsonManager() 
	{
		
	}
	
	/**
	 * 加载所有数据模板
	 */
	public void loadAllJson()
	{
		//加载所有英雄模板
		List<BaseTemplate> hlist =  loadJosonFile("Hero", "com.model.hero.HeroTemplate");
		HeroManager.getInstance().setHeroTemplateList(hlist);
		
		//加载所有道具模板
		List<BaseTemplate> itemlist =  loadJosonFile("Item", "com.model.item.ItemTemplate");
		ItemManager.getInstance().setImteTemplateList(itemlist);
		
		//加载所有副本模板
		List<BaseTemplate> dungeonlist =  loadJosonFile("Dungeon", "com.model.dungeon.DungeonTemplate");
		DungeonManager.getInstance().setDungeonTemplate(dungeonlist);
		
		//加载所有副本模板
		List<BaseTemplate> customlist =  loadJosonFile("Custom", "com.model.dungeon.CustomTemplate");
		DungeonManager.getInstance().setCustomTemplate(customlist);
		
		//加载所有副本模板
		List<BaseTemplate> courselist =  loadJosonFile("Course", "com.model.dungeon.CourseTemplate");
		DungeonManager.getInstance().setCourseTemplate(courselist);
		
		//加载所有阵型模板
		List<BaseTemplate> formationlist =  loadJosonFile("Formation", "com.model.formation.FormationTemplate");
		FormationManager.getInstance().setFormationTemplate(formationlist);
	}
	
	/**
	 * 加载json文件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<BaseTemplate> loadJosonFile(String templateName,String className)
	{
		List<BaseTemplate> objList = new ArrayList<>();
		
//		templateName = "Hero";
		String jsonPath = "/res/"+templateName+".json";
		
//		className = "com.model.hero.HeroTemplate";
		
		InputStream in = this.getClass().getResourceAsStream(jsonPath);
        String content;
		try {
			byte b[]=new byte[in.available()];
			in.read(b);
			in.close();
			content = new String(b,"UTF-8");
			
			@SuppressWarnings("rawtypes")
			Class forName = Class.forName(className);
			
			objList =  JSON.parseArray(content, forName);
			
//			for(int i=0,len=heroList.size();i<len;i++){
//				HeroTemplate temp=  (HeroTemplate) heroList.get(i);
//				System.out.printf("heroid:%s,heroname:%s\n",temp.heroid,temp.heroname);
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return objList;
	}
	
	public static void main(String[] args)
	{
		
		LoadJsonManager.getInstance().loadJosonFile("","");
	}
}
