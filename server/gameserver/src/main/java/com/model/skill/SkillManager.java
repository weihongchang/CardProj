package com.model.skill;

import java.util.ArrayList;
import java.util.List;

import com.model.BaseTemplate;

public class SkillManager {
	private static volatile SkillManager sInst = null;

	private SkillManager() {
	}

	public static SkillManager getInstance() {
		SkillManager inst = sInst;
		if (inst == null) {
			synchronized (SkillManager.class) {
				inst = sInst;
				if (inst == null) {
					inst = new SkillManager();
					sInst = inst;
				}
			}
		}
		return inst;
	}
	
	
	private List<BaseTemplate> skillTemplate = new ArrayList<>();
	
	
	
	
	
	public List<BaseTemplate> getSkillTemplate() {
		return skillTemplate;
	}

	public void setSkillTemplate(List<BaseTemplate> skillTemplate) {
		this.skillTemplate = skillTemplate;
	}

	/**
	 * 获取技能模板
	 * @param _id
	 * @return
	 */
	public SkillTemplate getSkillTemplate(int _id)
	{
		return null;
	}
}
