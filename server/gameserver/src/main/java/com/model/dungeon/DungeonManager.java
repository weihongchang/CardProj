package com.model.dungeon;

import java.util.ArrayList;
import java.util.List;

import com.model.BaseTemplate;

public class DungeonManager {
	private static volatile DungeonManager sInst = null;

	private DungeonManager() {
	}

	public static DungeonManager getInstance() {
		DungeonManager inst = sInst;
		if (inst == null) {
			synchronized (DungeonManager.class) {
				inst = sInst;
				if (inst == null) {
					inst = new DungeonManager();
					sInst = inst;
				}
			}
		}
		return inst;
	}
	
/*******************************************************************************/
/*******************************************************************************/
	private List<BaseTemplate> dungeonTemplate = new ArrayList();
	private List<BaseTemplate> customTemplate = new ArrayList();
	private List<BaseTemplate> courseTemplate = new ArrayList();


	
	
/**************************************************************************/
/**************************************************************************/
	public List<BaseTemplate> getDungeonTemplate() {
		return dungeonTemplate;
	}

	public void setDungeonTemplate(List<BaseTemplate> dungeonTemplate) {
		this.dungeonTemplate = dungeonTemplate;
	}

	public List<BaseTemplate> getCustomTemplate() {
		return customTemplate;
	}

	public void setCustomTemplate(List<BaseTemplate> customTemplate) {
		this.customTemplate = customTemplate;
	}

	public List<BaseTemplate> getCourseTemplate() {
		return courseTemplate;
	}

	public void setCourseTemplate(List<BaseTemplate> courseTemplate) {
		this.courseTemplate = courseTemplate;
	}
	
	
	/**
	 * 根据模板id获取副本阵型
	 * @param _id
	 * @return
	 */
	public CourseTemplate getCourseById(int _id)
	{
		for (int i = 0; i < courseTemplate.size(); i++) {
			CourseTemplate course =  (CourseTemplate)courseTemplate.get( i );
			if( course != null && course.courseid.equals( _id+"" ))
			{
				return course;
			}
		}
		return null;
	}
	
	
}

