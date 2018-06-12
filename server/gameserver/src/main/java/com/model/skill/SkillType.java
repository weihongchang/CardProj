package com.model.skill;
public enum SkillType
{
	skill_type_danti(1,"单体"),
	skill_type_heng(2,"横排"),
	skill_type_shu(3,"竖排"),
	skill_type_all(4,"全体");
	
	
	private SkillType(int _id ,String _name)
	{
		id = _id;
		name = _name;
	}
	
	public int getValue()
	{
		return id;
	}
	
	private int id;
	private String name;
}
