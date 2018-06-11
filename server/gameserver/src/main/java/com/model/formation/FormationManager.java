package com.model.formation;

import java.util.ArrayList;
import java.util.List;

import com.model.BaseTemplate;
import com.model.dungeon.CourseTemplate;

public class FormationManager {
	private static volatile FormationManager sInst = null;

	private FormationManager() {
	}

	public static FormationManager getInstance() {
		FormationManager inst = sInst;
		if (inst == null) {
			synchronized (FormationManager.class) {
				inst = sInst;
				if (inst == null) {
					inst = new FormationManager();
					sInst = inst;
				}
			}
		}
		return inst;
	}
	
	/**
	 * 阵型模板列表
	 */
	private List<BaseTemplate> formationTemplate = new ArrayList<>();

	public List<BaseTemplate> getFormationTemplate() {
		return formationTemplate;
	}

	public void setFormationTemplate(List<BaseTemplate> formationTemplate) {
		this.formationTemplate = formationTemplate;
	}
	
	
	
	
	/**
	 * 根据模板id获取阵型
	 * @param _id
	 * @return
	 */
	public FormationTemplate getFormationById(int _id)
	{
		for (int i = 0; i < formationTemplate.size(); i++) {
			FormationTemplate formation =  (FormationTemplate)formationTemplate.get( i );
			if( formation != null && formation.formationid.equals( _id+"" ))
			{
				return formation;
			}
		}
		return null;
	}
	
	/**
	 * 获取阵型战斗力
	 * @param formationID
	 * @return
	 */
	public int getFormationFightNum(int formationID)
	{
		
		return 0;
	}
}
