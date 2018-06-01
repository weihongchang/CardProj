package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.model.BaseTemplate;
import com.model.hero.HeroManager;
import com.model.hero.HeroTemplate;

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
	
	public void loadAllJson()
	{
		List<BaseTemplate> hlist =  loadJosonFile("Hero", "com.model.hero.HeroTemplate");
		HeroManager.getInstance().setHeroTemplateList(hlist);
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