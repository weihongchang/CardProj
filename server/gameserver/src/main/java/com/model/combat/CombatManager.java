package com.model.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.core.Message.Model.Message;
import com.core.Message.Model.Message.CSFightData;
import com.model.dungeon.CourseTemplate;
import com.model.dungeon.DungeonManager;
import com.model.formation.Formation;
import com.model.formation.FormationManager;
import com.model.formation.FormationTemplate;
import com.model.hero.Hero;
import com.model.hero.HeroManager;
import com.model.hero.HeroTemplate;
import com.model.player.Player;
import com.model.skill.SkillManager;
import com.model.skill.SkillTemplate;
import com.model.skill.SkillType;

public class CombatManager {
	private static volatile CombatManager sInst = null;
	
	private CombatManager() {
	}
	
	public static CombatManager getInstance() {
		CombatManager inst = sInst;
		if (inst == null) {
			synchronized (CombatManager.class) {
				inst = sInst;
				if (inst == null) {
					inst = new CombatManager();
					sInst = inst;
				}
			}
		}
		return inst;
	}
	
	
	private static int  stepTime = 1;
	
	
	/**
	 * 战斗
	 * @param thePlayer
	 * @param arenaType
	 *  combatType 1,副本;2,封官；3刷塔;4竞技场,5金矿,6世界boss，7群雄,8地牢
	 * @param userid
	 * @return
	 */
	public int FightBegin(Player thePlayer,int combatType,int userid,int value)
	{
		try{
	    int iconSelf = 20001;
        int iconTarget = 0;
        String sceneAnuId = "1";
        String scenePngId ="1,5";
        int HpSelf = 0;
        int HpTarget = 0;
        String selfName = thePlayer.getName();
        String targetName = "";
//        thePlayer.addReputation(1000);
        Player targetPlayer = null;
        
        FormationTemplate df = null;
		int formationId = 0;
		int courseId = userid;
    	courseId = 30;
    	
    	//跳过战斗;1不可跳过.0可跳过
    	int jumpAttack = 1;
	    {
	    	int fightNum = 0;
	    	if( combatType == 1 )
	    	{
	    		//副本
	    		CourseTemplate course = DungeonManager.getInstance().getCourseById(courseId);
		    	if( course != null )
		    	{
		    		
		    		formationId = Integer.parseInt( course.formationid);
		    	}
		    	
		    	df = FormationManager.getInstance().getFormationById(formationId);
		    	fightNum = FormationManager.getInstance().getFormationFightNum( formationId );
	    	}
	    	
	    		
//	    		List<UST_SOLDIERLIST_COMBAT_NEWATTACK> soldierList = new ArrayList<UST_SOLDIERLIST_COMBAT_NEWATTACK>();
//	    		List<UST_HERODETAILLIST_COMBAT_NEWATTACK> heroDetailList = new ArrayList<UST_HERODETAILLIST_COMBAT_NEWATTACK>();
	    		
	    	Formation targetFormation = new Formation(df);
    		List<CombatNodeData> combatNodeList = null;
    		combatNodeList = CombatManager.getInstance().getAtkHeroAndInfo(combatType, thePlayer, targetPlayer, targetFormation);
    	    	
    	    	//战斗类型.0pve;1pvp
    	    	int pvptype = 0;
//    	    	PvpPlayerData pvpData = new PvpPlayerData(df, fightNum);
//
//    	    	if( pvpData != null && pvpData.getFormation() != null )
//    	    	{
//    	    		combatNodeList = CombatManager.getInstance().getAtkHeroAndInfo(pvptype, thePlayer, targetPlayer, soldierList, heroDetailList,pvpData);
//    	    	}
//    	    	else
//				{
//    	    		//没有对手直接胜利
//				}


    	        int resultValue = 0;
    	        cResult result = CombatManager.getInstance().getNewResult();
    	        //计算战斗过程
    	        List<CSFightData.Builder> atkLog = 
    	        	CombatManager.getInstance().getAtkLogPVP( combatNodeList,  thePlayer, targetPlayer, result);
    	        
    	        if( result.getValue() == 1 )
    	        {
    	        	resultValue = 0;
    	        }
    	        else if( result.getValue() == 2 )
    	        {
    	        	resultValue = 1;
    	        }
    	        
    	        Result checkResult = CombatManager.getInstance().FightResultHandle(thePlayer, resultValue,combatType , userid);
		        
		        Message.SCFight.Builder msg = Message.SCFight.newBuilder();
		        msg.setHpSelf(HpSelf);
		        msg.setHpTarget(HpTarget);
		        msg.setResult(resultValue);
		        for (int i = 0; i < atkLog.size(); i++) {
		        	msg.addCombatData(atkLog.get( i ));
				}
		        
		        thePlayer.sendMessage(msg.build());
		        

    		    //战斗录像
//    		    int Fightid = CombatService.getInstance().getFightingMaxID();
//				FightingData fd = new FightingData(Fightid, iconSelf, iconTarget, sceneAnuId, scenePngId, 
//						selfName,targetName,HpSelf, HpTarget, resultValue, soldierList, heroDetailList, atkLog);
//				CombatService.getInstance().addFightingData(fd);
				
    		    //战斗处理
				combatResultManage(resultValue, combatType, userid, thePlayer);
	    	}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	    return 0;
	}
	
	/**
	 * 战斗结果处理
	 */
	public void combatResultManage(int resultValue, int arenaType,int userid,Player thePlayer)
	{

		if( resultValue == 0 )
		{
			//胜利处理
			if( arenaType == 4 )
			{}
		}
		else
		{
			//失败
			
		}
	}
	
	
	/**
	 * 获取双方参战人员数据
	 * type 战斗类型.0,pve;1,pvp
	 * @param thePlayer		攻击者
	 * @param targetPlayer	被攻击者
	 * @return
	 */
	public List<CombatNodeData> getAtkHeroAndInfo(int type,Player thePlayer,Player targetPlayer,Formation formationTarget
			)
	{

		List<CombatNodeData> combatNodeList = new ArrayList<CombatNodeData>();
		for( int i =0 ;i<18;i++ )
		{
			combatNodeList.add( new CombatNodeData());
		}
		// 己方阵型
		Formation formation = thePlayer.getFormation();
		for(int i = 0;i < formation.getFormation().length;i++)
		{
//			UST_SOLDIERLIST_COMBAT_NEWATTACK us = new UST_SOLDIERLIST_COMBAT_NEWATTACK();
//			// index
//			us.boxIndex = i;
//			// 英雄id
//			us.heroID = formation.getFormation()[i];
			
			combatNodeList.get( i ).boxIndex = i;
			combatNodeList.get( i ).heroID = formation.getFormation()[i];
			
//			soldierList.add(us);
		}

		
		// 对方阵型
		for(int i = 9;i < formationTarget.getFormation().length + 9;i++)
		{
//			UST_SOLDIERLIST_COMBAT_NEWATTACK us = new UST_SOLDIERLIST_COMBAT_NEWATTACK();
//			// index
//			us.boxIndex = i;
//			// 英雄id
//			us.heroID = formationTarget.getFormation()[i - 9];
			
			combatNodeList.get( i ).boxIndex = i;
			combatNodeList.get( i ).heroID = formationTarget.getFormation()[i - 9];//us.heroID;
			
//			soldierList.add(us);
		}

		// 英雄数据
		for(int i = 0;i < combatNodeList.size();i++)
		{
			CombatNodeData us = combatNodeList.get(i);
			if(us.heroID > 0)
			{
				HeroTemplate hero = null;
				Hero h = null;
				int templateID = 0;
				if(us.boxIndex < 9)
				{
					h = thePlayer.getHeroForID(us.heroID);
					if(h != null)
					{
						templateID = h.getTemplateID();
					}	
				}
				else
				{
					templateID = us.heroID;
				}
				
				
				hero = HeroManager.getInstance().getHeroTemplateForID(templateID);
				if(hero != null)
				{
					//战斗用的数据
					combatNodeList.get( i ).name = hero.heroname;
					combatNodeList.get( i ).AnuId = Integer.parseInt( hero.anuid);
					combatNodeList.get( i ).PngId = hero.pngid;
					combatNodeList.get( i ).iconId =Integer.parseInt( hero.heroiconid);
					combatNodeList.get( i ).bigiconId = Integer.parseInt(hero.heroiconid);
					combatNodeList.get( i ).modelWidth = Integer.parseInt(hero.pngwidth);
					combatNodeList.get( i ).modelHeight = Integer.parseInt(hero.pngheight);
					combatNodeList.get( i ).attackframe = 1;
					combatNodeList.get( i ).hFlyAnuId = 1;
					combatNodeList.get( i ).hFlyPngId = 1;
					combatNodeList.get( i ).hFlyPngX = 1;
					combatNodeList.get( i ).hFlyPngY = 1;
					combatNodeList.get( i ).hFlyHeight = 1;
					combatNodeList.get( i ).hFlySpeed = 1;
					combatNodeList.get( i ).hFlyAngle = 1;
					combatNodeList.get( i ).pngHeight = Integer.parseInt(hero.pngheight);
					combatNodeList.get( i ).pngWidth = Integer.parseInt(hero.pngwidth);
					combatNodeList.get( i ).heroColor = Integer.parseInt(hero.color);
					combatNodeList.get( i ).soldierNum = Integer.parseInt(hero.soldiernum);
					
//					if(us.boxIndex < 9)
//					{
//						combatNodeList.get( i ).hp = h.getHp();
//						combatNodeList.get( i ).def = h.getDef();
//						combatNodeList.get( i ).atk = h.getAtk();
//						combatNodeList.get( i ).fightAtk = h.getFightAtk();
//						combatNodeList.get( i ).fightDef = h.getFightDef();
//					}
//					else
					{
						combatNodeList.get( i ).hp = Integer.parseInt(hero.hp);
						combatNodeList.get( i ).def = Integer.parseInt(hero.def);
						combatNodeList.get( i ).atk = Integer.parseInt(hero.atk);
						combatNodeList.get( i ).fightAtk = 1;
						combatNodeList.get( i ).fightDef = 1;
					}
					
					combatNodeList.get( i ).skillID = 0;//h.getSkill().getSkillID();
					combatNodeList.get( i ).herovio = Integer.parseInt(hero.herovio);	
					combatNodeList.get( i ).herodes = Integer.parseInt(hero.herodes);	
					combatNodeList.get( i ).herores = Integer.parseInt(hero.herores);	
					combatNodeList.get( i ).heroten = Integer.parseInt(hero.heroten);	
					combatNodeList.get( i ).herodod = Integer.parseInt(hero.herodod);	
					combatNodeList.get( i ).herotar = Integer.parseInt(hero.herotar);	
					combatNodeList.get( i ).heroang = 1;
				}
			}

		}
		return combatNodeList;
	}
	

	/**
	 * 战斗结果
	 * @param thePlayer		攻击者
	 * @param targetPlayer	被攻击者
	 * @return
	 */
	public List<CSFightData.Builder> getAtkLogPVP(List<CombatNodeData> soldierList, 
			Player thePlayer,Player targetPlayer,cResult cresult)
	{
//		if( thePlayer != null && targetPlayer != null )
		{
    		//战斗过程
    		List<Integer> hplist = new ArrayList<Integer>();
    		List<Integer> nuqilist = new ArrayList<Integer>();
    		for( int i=0;i<18;i++ )
    		{
    			hplist.add(0);
    			nuqilist.add(50);
    		}
    		
    		for( int i=0;i<soldierList.size();i++ )
    		{
    			CombatNodeData us = soldierList.get( i );
    			if( us != null )
    			{
    				int index = us.boxIndex;
    				int hp = 0;
    				if( us.heroID > 0 )
    				{
    					hp = us.hp;
    				}
    				if( index >=0 && hp >0 )
    				{
    					hplist.set(index, hp);
    				}
    			}
    		}
    		
    		List<CSFightData.Builder> atkLog = new ArrayList<CSFightData.Builder>();
    		
    		int result = 0;
    		int boutNum = 0;
    		while( (result = isWinner(hplist)) == 0 )
    		{
    			//一回合战斗
    			boutNum++;
    			int atkStep = 0;
    			int defStep = 0;
    			for( int i=0;i<9;i++ )
    			{
    				//攻方
    				if( atkStep >= i )
    				{
    					if( i>0 )
    					{
    						atkStep++;
    					}
    				}
    				else
    				{
    					atkStep = i;
    				}
    				while( atkStep <9-1 && hplist.get( atkStep ) <=0  )
    				{
    					atkStep++;
    				}
    				if( atkStep <9 && hplist.get(atkStep) > 0 )
    				{
//    					HeroLevelConfig atkHero =  getHeroForIndex( soldierList,atkStep,thePlayer,targetPlayer );
    					CombatNodeData atkHero = soldierList.get(atkStep);
    					if( atkHero != null )
    					{
    						int targetStep = getAtkTargetIndex( hplist,atkStep );
    						if( targetStep <0 )
    						{
    							break;
    							
    						}
    						if( hplist.get(targetStep) >0 )
        						{
//        							HeroLevelConfig defHero =  getHeroForIndex( soldierList,targetStep,thePlayer,targetPlayer );
        							CombatNodeData defHero = soldierList.get(targetStep);
        							if( defHero != null )
        							{
        								Random	rand = new Random();
        								
            							//第一回合
        								Message.CSFightData.Builder ua1 = Message.CSFightData.newBuilder();
//            				    		ua1.boutNum = boutNum;
            				    		//攻击英雄index
            				    		ua1.setHeroIdAtk( atkStep);
            				    		
            				    		//攻击类型；1普通攻击，2技能攻击，3反击
            				    		ua1.setAtkType(1);
            				    		
            				    		//被攻击英雄index
            				    		ua1.setHeroIdTarget("");
            				    		//攻击效果；0,无效果,1暴击，2闪避
            				    		ua1.setAtkEffect("");
            				    		ua1.setHp("");
            				    		ua1.setDie("");
            				    		
            				    		
//            				    		int[][] hurt = new int[9][2];
            				    		//range 1.普通攻击，2技能攻击
            				    		int range = 1;//rand.nextInt(4)+1;
            				    		int nuqi = nuqilist.get(atkStep);
            				    		SkillTemplate skill = null;
            				    		if( nuqi >= 100 )
            				    		{
            				    			range = 2;
            				    			nuqilist.set(atkStep, 0);
            				    			//技能攻击
            				    			int skillID = atkHero.skillID;
            				    			skill = SkillManager.getInstance().getSkillTemplate(skillID);
            				    		}
            				    		ua1.setSkillrange( range);
            				    		//左边是否被攻击
            				    		boolean isLeft = false;
            				    		boolean isGuwu = false;
//            				    		if( thePlayer.getQunxiongGuwu() >0 )
//            				    		{
//            				    			isGuwu = true;
//            				    		}
            				    		int[][] hurt = attack(atkHero, range, targetStep, isLeft, soldierList, 
            				    				thePlayer, targetPlayer,hplist,isGuwu,nuqi);
            				    		
            				    		for( int hurtIndex=0;hurtIndex<hurt.length;hurtIndex++ )
            				    		{
            				    			int heroIdTarget = hurt[hurtIndex][0];
            				    			int atkEffect = hurt[hurtIndex][1];
            				    			int hp = hurt[hurtIndex][2];
            				    			
            				    			if( heroIdTarget == 0 && atkEffect ==0 && hp == 0 )
            				    			{
            				    				continue;
            				    			}
            				    			int currentHp = hplist.get(heroIdTarget);
            				    			if( currentHp <=0 )
            				    			{
            				    				continue;
            				    			}
            				    			else
            				    			{
                				    			int die = 0;
                    				    		//是否死亡
                    				    		currentHp -= hp;
                    				    		if( currentHp <0 ) currentHp = 0;
                    				    		hplist.set(heroIdTarget, currentHp);
                    				    		if( range != 2 )
                    				    		{
                    				    			int oldnuqi = nuqilist.get(atkStep);
                    				    			nuqilist.set(atkStep, oldnuqi+25);
                    				    		}
                    				    		
                    				    		int oldnuqi = nuqilist.get(heroIdTarget);
                    				    		int newnuqi = oldnuqi+25;
                    				    		nuqilist.set(heroIdTarget, newnuqi);
                    				    		
                    				    		if( currentHp == 0 )
                    				    			die = 1;
                    				    		
                    				    		
                    				    		String _hitarget = ua1.getHeroIdTarget()+heroIdTarget+","+atkEffect+","+hp+","+die+","+newnuqi;
                    				    		ua1.setHeroIdTarget(_hitarget);
                    				    		
            				    			}
            				    		}
            				    		
            				    		//下次攻击时间,秒
            				    		ua1.setSleepTime(stepTime);
            				    		
            				    		ua1.setSkillanu( 11);
            				    		ua1.setSkillpng( "11");
//            				    		ua1.skillNameAnu = 0;
//            				    		ua1.skillNamePng="";
            				    		if( skill != null )
            				    		{
            				    			ua1.setSkillanu( Integer.parseInt( skill.skillanu ));
                				    		ua1.setSkillpng( skill.skillpng);
//                				    		ua1.skillNameAnu = skill.getSkillnameanu();
//                				    		ua1.skillNamePng=skill.getSkillnamepng();
//                				    		ua1.skillAttackStep = skill.getAtkframe();
            				    		}
//            				    		ua1.nuqi =  nuqilist.get(atkStep);
            				    		
            				    		atkLog.add(ua1);
//            				    		break;
        							}
        						}

    					}
    				}
    				
    				//守方反击
    				if( defStep>= i+9 )
    				{
    					if( i+9 >9 )
    					{
    						defStep++;
    					}
    				}
    				else
    				{
    					defStep = i+9;
    				}
    				
    				while( defStep <17 && hplist.get( defStep ) <=0  )
    				{
    					defStep++;
    				}

    				if(defStep<18 && hplist.get(defStep) > 0 )
    				{
//    					HeroLevelConfig atkHero =  getHeroForIndex( soldierList,defStep,thePlayer,targetPlayer );
    					CombatNodeData atkHero = soldierList.get(defStep);
    					if( atkHero != null )
    					{
    						int targetStep = getAtkTargetIndex(hplist, defStep);
    						if( targetStep <0 )
    						{
    							break;
    							
    						}

    						if( hplist.get(targetStep) >0 )
        						{
//        							HeroLevelConfig defHero =  getHeroForIndex( soldierList,targetStep,thePlayer,targetPlayer );
        							CombatNodeData defHero = soldierList.get(targetStep);
        							if( defHero != null )
        							{
        								Random	rand = new Random();
        								
            							//第一回合
            				    		Message.CSFightData.Builder ua1 = Message.CSFightData.newBuilder();
//            				    		ua1.boutNum = boutNum;
            				    		//攻击英雄index
            				    		ua1.setHeroIdAtk( defStep);
            				    		
            				    		
            				    		//攻击类型；1普通攻击，2技能攻击，3反击
            				    		ua1.setAtkType( 1);
            				    		
            				    		//被攻击英雄index
            				    		ua1.setHeroIdTarget( "");
            				    		//攻击效果；0,无效果,1暴击，2闪避
            				    		ua1.setAtkEffect("");
            				    		ua1.setHp("");
            				    		ua1.setDie("");
            				    		
            				    		//range 1.普通攻击，2技能攻击
            				    		int range = 1;//rand.nextInt(4)+1;
            				    		int nuqi = nuqilist.get(defStep);
            				    		SkillTemplate skill = null;
            				    		if( nuqi >= 100 )
            				    		{
            				    			range = 2;
            				    			nuqilist.set(defStep, 0);
            				    			
            				    			//技能攻击
            				    			int skillID = atkHero.skillID;
            				    			skill = SkillManager.getInstance().getSkillTemplate(skillID);
            				    		}
            				    		ua1.setSkillrange( range);
            				    		
            				    		//左边是否被攻击
            				    		boolean isLeft = true;
            				    		boolean isGuwu = false;
            				    		if( targetPlayer != null  )
            				    		{
//                				    		if( targetPlayer.getQunxiongGuwu() >0 )
//                				    		{
//                				    			isGuwu = true;
//                				    		}
            				    		}
            				    		int[][] hurt = attack(atkHero, range, targetStep, isLeft, soldierList,  thePlayer, targetPlayer,hplist,isGuwu,nuqi);
            				    		
            				    		for( int hurtIndex=0;hurtIndex<hurt.length;hurtIndex++ )
            				    		{
            				    			int heroIdTarget = hurt[hurtIndex][0];
            				    			int atkEffect = hurt[hurtIndex][1];
            				    			int hp = hurt[hurtIndex][2];
            				    			
            				    			if( heroIdTarget == 0 && atkEffect ==0 && hp == 0 )
            				    			{
            				    				continue;
            				    			}
            				    			
            				    			int currentHp = hplist.get(heroIdTarget);
            				    			if( currentHp <=0 )
            				    			{
            				    				continue;
            				    			}
            				    			else
            				    			{
                				    			int die = 0;
                    				    		//是否死亡
                    				    		currentHp -= hp;
                    				    		if( currentHp <0 ) currentHp = 0;
                    				    		hplist.set(heroIdTarget, currentHp);
                    				    		if( range != 2 )
                    				    		{
                    				    			int oldNuqi = nuqilist.get(defStep);
                    				    			nuqilist.set(defStep, oldNuqi+25);
                    				    		}
                    				    		int oldNuqi = nuqilist.get(heroIdTarget);
                    				    		int newnuqi = oldNuqi+25;
                    				    		nuqilist.set(heroIdTarget, newnuqi);
                    				    		
                    				    		if( currentHp == 0 )
                    				    			die = 1;
                    				    		
                    				    		String _hitarget = ua1.getHeroIdTarget()+heroIdTarget+","+atkEffect+","+hp+","+die+","+newnuqi;
                    				    		ua1.setHeroIdTarget(_hitarget);
            				    			}
            				    		}
            				    		//下次攻击时间,秒
            				    		ua1.setSleepTime(stepTime);
            				    		
            				    		ua1.setSkillanu( 11);
            				    		ua1.setSkillpng( "11");
//            				    		ua1.skillNameAnu = 0;
//            				    		ua1.skillNamePng="";
            				    		if( skill != null )
            				    		{
            				    			ua1.setSkillanu( Integer.parseInt( skill.skillanu ));
                				    		ua1.setSkillpng( skill.skillpng);
//                				    		ua1.skillNameAnu = skill.getSkillnameanu();
//                				    		ua1.skillNamePng=skill.getSkillnamepng();
//                				    		ua1.skillAttackStep = skill.getAtkframe();
            				    		}
//            				    		ua1.nuqi =  nuqilist.get(atkStep);
            				    		
            				    		atkLog.add(ua1);
        							}
        						}
    					}
    				}
    				if( defStep >=18 )
    				{
    					defStep = 17;
    				}
    			}
    		}
    		
    		cresult.retValue = result;
    		return atkLog;
		}
//		return null;
	}
	
	/**
	 * 是否有胜出者
	 * @param hplist
	 * @return
	 */
	private int isWinner( List<Integer> hplist )
	{
		int allHp = 0;
		for( int i=0;i<9;i++ )
		{
			allHp += hplist.get(i );
		}
		if( allHp <= 0 )
		{
			return 2;
		}
		allHp = 0;
		for( int i=9;i<18;i++ )
		{
			allHp+= hplist.get( i );
		}
		
		if( allHp <=0 )
		{
			return 1;
		}
		
		return 0;
	}
	
	/**
	 *
	 * @param hplist
	 * @param atkIndex
	 * @return
	 */
	private int getAtkTargetIndex(List<Integer> hplist,int atkIndex)
	{
		if( atkIndex <9 )
		{
			for( int k=0;k<3;k++ )
			{
    			if( atkIndex == k || atkIndex == k+3 || atkIndex == k+6)
    			{
    				if( hplist.get(k+9) > 0 )
    				{
    					return k+9;
    				}
    				else if( hplist.get(k+12) > 0 )
    				{
    					return k+12;
    				}
    				else if( hplist.get(k+15) > 0 )
    				{
    					return k+15;
    				}
    				else
    				{
    					for( int i=9;i<18;i++ )
    					{
    						if( hplist.get(i) >0 )
    						{
    							return i;
    						}
    					}
    				}
    			}
			}
		}
		else if( atkIndex >= 9 )
		{
			for( int k=0;k<3;k++ )
			{
    			if( atkIndex == k+9 || atkIndex == k+12 || atkIndex == k+15 )
    			{
    				if( hplist.get(k) > 0 )
    				{
    					return k;
    				}
    				else if( hplist.get(k+3) > 0 )
    				{
    					return k+3;
    				}
    				else if( hplist.get(k+6) > 0 )
    				{
    					return k+6;
    				}
    				else
    				{
    					for( int i=0;i<9;i++ )
    					{
    						if( hplist.get(i) >0 )
    						{
    							return i;
    						}
    					}
    				}
    			}
			}
		}
		return -1;
	}
	
	
	/**
	 * 攻击
	 * @param atkHero
	 * @param defHero
	 * @param range
	 * @param targetStep
	 * @param isLeft 是否左边被攻击
	 * @return
	 * int[0] 攻击的英雄位置
	 * int[1] 攻击效果
	 * int[2] 攻击伤害
	 */
//	public int[][] attack(HeroLevelConfig atkHero,int range,int targetStep,boolean isLeft,
//			List<UST_SOLDIERLIST_COMBAT_NEWATTACK> soldierList,Player player,Player targetPlayer,
//			List<Integer> hplist,boolean isGuwu,int nuqi)
//	{
//		int[][] hurt = new int[9][3];
//		int skillRange = 1;
//		SkillTemplate skill = null;
//		if( range == 2 )
//		{
//			//技能攻击
//			int skillID = atkHero.getSkillId();
//			skill = SkillManager.getInstance().getSkillTemplate(skillID);
//			if( skill != null )
//			{
//				skillRange =  skill.getSkillType().getValue();
//			}
//		}
//		
//		if( skillRange == SkillType.skill_type_danti.getValue() )
//		{
////			int[0] 伤害
////			int[1] 攻击效果
//			if( hplist.get(targetStep) > 0 )
//			{
//    			HeroLevelConfig defHero = getHeroForIndex(soldierList, targetStep, player, targetPlayer);
//    			if( defHero != null )
//    			{
//        			int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
//        			hurt[0][0] = targetStep;
//        			hurt[0][1] = atkHurt[1];
//        			hurt[0][2] = atkHurt[0];
//    			}
//			}
//			
//		}
//		else if( skillRange == SkillType.skill_type_heng.getValue() )
//		{
//			int[] index1 = new int[3];
//			if( targetStep % 3 == 0)
//			{
//				//最上边一行
//				if(!isLeft)
//				{
//					index1[0] = 9;
//    				index1[1] = 12;
//    				index1[2] = 15;
//				}
//				else
//				{
//					index1[0] = 0;
//    				index1[1] = 3;
//    				index1[2] = 6;
//				}
//			}
//			else if( targetStep % 3 == 1 )
//			{
//				//中间行
//				if(!isLeft)
//				{
//					index1[0] = 10;
//    				index1[1] = 13;
//    				index1[2] = 16;
//				}
//				else
//				{
//					index1[0] = 1;
//    				index1[1] = 4;
//    				index1[2] = 7;
//				}
//			}
//			else if( targetStep % 3 == 2 )
//			{
//				//最下边行
//				if(!isLeft)
//				{
//					index1[0] = 11;
//    				index1[1] = 14;
//    				index1[2] = 17;
//				}
//				else
//				{
//					index1[0] = 2;
//    				index1[1] = 5;
//    				index1[2] = 8;
//				}
//			}
//			
//			for( int i=0;i<3;i++ )
//			{
//				int heroIndex = index1[i];
//				if( hplist.get(heroIndex) > 0 )
//				{
//    				HeroLevelConfig defHero = getHeroForIndex(soldierList, heroIndex, player, targetPlayer);
//    				if( defHero != null )
//    				{
//        				int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
//        				hurt[i][0] = heroIndex;
//        				hurt[i][1] = atkHurt[1];
//        				hurt[i][2] = atkHurt[0];
//    				}
//				}
//			}
//		}
//		else if( skillRange == SkillType.skill_type_shu.getValue() )
//		{
//			int[] index1 = new int[3];
//			index1[0] = targetStep;
//			if( targetStep % 3 == 0)
//			{
//				//最上边一行
//				index1[1] = targetStep + 1;
//				index1[2] = targetStep + 2;
//			}
//			else if( targetStep % 3 == 1 )
//			{
//				//中间行
//				index1[1] = targetStep - 1;
//				index1[2] = targetStep + 1;
//			}
//			else if( targetStep % 3 == 2 )
//			{
//				//最下边行
//				index1[1] = targetStep - 1;
//				index1[2] = targetStep - 2;
//			}
//			
//			for( int i=0;i<3;i++ )
//			{
//				int heroIndex = index1[i];
//				if( hplist.get(heroIndex) > 0 )
//				{
//    				HeroLevelConfig defHero = getHeroForIndex(soldierList, heroIndex, player, targetPlayer);
//    				if( defHero != null )
//    				{
//        				int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
//        				hurt[i][0] = heroIndex;
//        				hurt[i][1] = atkHurt[1];
//        				hurt[i][2] = atkHurt[0];
//    				}
//				}
//			}
//		}
//		else if( skillRange == SkillType.skill_type_all.getValue() )
//		{
//			if( !isLeft )
//			{
//				for( int i=9;i<18;i++ )
//				{
//					if( hplist.get(i) > 0 )
//					{
//    					HeroLevelConfig defHero = getHeroForIndex(soldierList, i, player, targetPlayer);
//    					if( defHero != null )
//    					{
//            				int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
//            				hurt[i-9][0] = i;
//            				hurt[i-9][1] = atkHurt[1];
//            				hurt[i-9][2] = atkHurt[0];
//    					}
//					}
//				}
//			}
//			else
//			{
//				for( int i=0;i<9;i++ )
//				{
//					if( hplist.get(i) > 0 )
//					{
//    					HeroLevelConfig defHero = getHeroForIndex(soldierList, i, player, targetPlayer);
//    					if( defHero != null )
//    					{
//            				int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
//            				hurt[i][0] = i;
//            				hurt[i][1] = atkHurt[1];
//            				hurt[i][2] = atkHurt[0];
//    					}
//					}
//				}
//			}
//		}
//		return hurt;
//	}
	
	
	/**
	 * 攻击
	 * @param atkHero
	 * @param defHero
	 * @param range
	 * @param targetStep
	 * @param isLeft 是否左边被攻击
	 * @return
	 * int[0] 攻击的英雄位置
	 * int[1] 攻击效果
	 * int[2] 攻击伤害
	 */
	public int[][] attack(CombatNodeData atkHero,int range,int targetStep,boolean isLeft,
			List<CombatNodeData> soldierList,Player player,Player targetPlayer,
			List<Integer> hplist,boolean isGuwu,int nuqi)
	{
		int[][] hurt = new int[9][3];
		int skillRange = 1;
		SkillTemplate skill = null;
		if( range == 2 )
		{
			//技能攻击
			int skillID = atkHero.skillID;
			skill = SkillManager.getInstance().getSkillTemplate(skillID);
			if( skill != null )
			{
//				skillRange =  skill.getSkillType().getValue();
			}
		}
		
		if( skillRange == SkillType.skill_type_danti.getValue() )
		{
//			int[0] 伤害
//			int[1] 攻击效果
			if( hplist.get(targetStep) > 0 )
			{
//    			CombatNodeData defHero = getHeroForIndex(soldierList, targetStep, player, targetPlayer);
    			CombatNodeData defHero = soldierList.get(targetStep);
    			if( defHero != null )
    			{
        			int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
        			hurt[0][0] = targetStep;
        			hurt[0][1] = atkHurt[1];
        			hurt[0][2] = atkHurt[0];
    			}
			}
			
		}
		else if( skillRange == SkillType.skill_type_heng.getValue() )
		{
			int[] index1 = new int[3];
			if( targetStep % 3 == 0)
			{
				//最上边一行
				if(!isLeft)
				{
					index1[0] = 9;
    				index1[1] = 12;
    				index1[2] = 15;
				}
				else
				{
					index1[0] = 0;
    				index1[1] = 3;
    				index1[2] = 6;
				}
			}
			else if( targetStep % 3 == 1 )
			{
				//中间行
				if(!isLeft)
				{
					index1[0] = 10;
    				index1[1] = 13;
    				index1[2] = 16;
				}
				else
				{
					index1[0] = 1;
    				index1[1] = 4;
    				index1[2] = 7;
				}
			}
			else if( targetStep % 3 == 2 )
			{
				//最下边行
				if(!isLeft)
				{
					index1[0] = 11;
    				index1[1] = 14;
    				index1[2] = 17;
				}
				else
				{
					index1[0] = 2;
    				index1[1] = 5;
    				index1[2] = 8;
				}
			}
			
			for( int i=0;i<3;i++ )
			{
				int heroIndex = index1[i];
				if( hplist.get(heroIndex) > 0 )
				{
//    				HeroLevelConfig defHero = getHeroForIndex(soldierList, heroIndex, player, targetPlayer);
    				CombatNodeData defHero = soldierList.get(heroIndex);
    				if( defHero != null )
    				{
        				int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
        				hurt[i][0] = heroIndex;
        				hurt[i][1] = atkHurt[1];
        				hurt[i][2] = atkHurt[0];
    				}
				}
			}
		}
		else if( skillRange == SkillType.skill_type_shu.getValue() )
		{
			int[] index1 = new int[3];
			index1[0] = targetStep;
			if( targetStep % 3 == 0)
			{
				//最上边一行
				index1[1] = targetStep + 1;
				index1[2] = targetStep + 2;
			}
			else if( targetStep % 3 == 1 )
			{
				//中间行
				index1[1] = targetStep - 1;
				index1[2] = targetStep + 1;
			}
			else if( targetStep % 3 == 2 )
			{
				//最下边行
				index1[1] = targetStep - 1;
				index1[2] = targetStep - 2;
			}
			
			for( int i=0;i<3;i++ )
			{
				int heroIndex = index1[i];
				if( hplist.get(heroIndex) > 0 )
				{
//    				HeroLevelConfig defHero = getHeroForIndex(soldierList, heroIndex, player, targetPlayer);
					CombatNodeData defHero = soldierList.get(heroIndex);
    				if( defHero != null )
    				{
        				int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
        				hurt[i][0] = heroIndex;
        				hurt[i][1] = atkHurt[1];
        				hurt[i][2] = atkHurt[0];
    				}
				}
			}
		}
		else if( skillRange == SkillType.skill_type_all.getValue() )
		{
			if( !isLeft )
			{
				for( int i=9;i<18;i++ )
				{
					if( hplist.get(i) > 0 )
					{
//    					HeroLevelConfig defHero = getHeroForIndex(soldierList, i, player, targetPlayer);
    					CombatNodeData defHero = soldierList.get(i);
    					if( defHero != null )
    					{
            				int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
            				hurt[i-9][0] = i;
            				hurt[i-9][1] = atkHurt[1];
            				hurt[i-9][2] = atkHurt[0];
    					}
					}
				}
			}
			else
			{
				for( int i=0;i<9;i++ )
				{
					if( hplist.get(i) > 0 )
					{
//    					HeroLevelConfig defHero = getHeroForIndex(soldierList, i, player, targetPlayer);
						CombatNodeData defHero = soldierList.get(i);
    					if( defHero != null )
    					{
            				int[] atkHurt = hurt(atkHero, defHero,skill,isGuwu,nuqi);
            				hurt[i][0] = i;
            				hurt[i][1] = atkHurt[1];
            				hurt[i][2] = atkHurt[0];
    					}
					}
				}
			}
		}
		return hurt;
	}
	
	
	/**
	 * 计算伤害
	 * @param atkHero
	 * @param defHero
	 * @return
	 * int[0] 伤害
	 * int[1] 攻击效果
	 */
	public int[] hurt(CombatNodeData atkHero,CombatNodeData defHero,SkillTemplate skill,boolean isGuwu,int nuqi)
	{
		Random rand = new Random();
		int[] hurt = new int[3];
		//伤害
		int atkEffect = 0;
		int atkAdd = 0;
		
		int hp = 0;
		if( skill != null )
		{
			int skillMultiple = 100;//skill.getMultiple()
			
			hp = (int) ( (float)( (atkHero.atk+atkHero.fightAtk) - (defHero.def+defHero.fightDef) ) * skillMultiple /100 * ( nuqi/100) );
		}
		else
		{
			hp = atkHero.atk+atkAdd - defHero.def;
		}
		if( hp <= 0  ) hp = 1;
		
		if( isGuwu )
		{
			hp += (int)(hp*0.2);
		}
		
//		int hp = atkHero.atk - defHero.def;
//		if( hp <= 0  ) hp = 1;
		
//		命中几率=攻击方命中-被攻击方闪避， 当命中几率≥95%,95%几率命中，反之当命中几率<5，则5%几率命中。
//		（即攻击方最低有5%的命中率，而被攻击方最低也会有5%几率的闪避）
		int defShanbi = defHero.herodod;
		if( defShanbi < 5 ) defShanbi = 5;
		int mingzhong = atkHero.herotar - defShanbi + 95;
		if( mingzhong <5 ) mingzhong =5;
		
		
		int r = rand.nextInt(100)+1;
		boolean isShanbi = false;
		//是否闪避
		if(r > mingzhong)
		{
			isShanbi = true;
			hp = 0;
			atkEffect = 2;
		}
		
//		暴击几率=攻击方的暴击-被攻击方的韧性，当暴击几率≥100时，
//		100%暴击，当暴击几率≤0时，则不暴击
		int baoji = atkHero.herovio - defHero.heroten;
		if( !isShanbi )
		{
    		if( baoji >0 )
    		{
    			r = rand.nextInt(100)+1;
    			if( r <= baoji )
    			{
//    	总暴击的攻击倍数=(200+（攻击方总破击-被攻击方总抗性）)/100，
//    	小数位保留2位计算伤害。总暴击的攻击倍数最低为2.
//    				System.out.println("baoji");
    				hp=hp*( (200+(atkHero.herodes - defHero.herores ))/100 );
    				atkEffect = 1;
    			}
    		}
		}
		
		hurt[0] = hp;
		hurt[1] = atkEffect;
		
		return hurt;
	}
	
	/**
	 * 计算伤害
	 * @param atkHero
	 * @param defHero
	 * @return
	 * int[0] 伤害
	 * int[1] 攻击效果
	 */
//	public int[] hurt(HeroLevelConfig atkHero,HeroLevelConfig defHero,SkillTemplate skill,boolean isGuwu,int nuqi)
//	{
//		Random rand = new Random();
//		int[] hurt = new int[3];
//		//伤害
//		int atkEffect = 0;
//		
//		int hp = 0 ;
//		if( skill != null )
//		{
//			int skillMultiple = 100;//skill.getMultiple()
//			hp = (int) ( (float)( (atkHero.getAtk()+atkHero.fightAtk) - (defHero.getDef()+defHero.fightDef) ) * skillMultiple /100 * ( nuqi/100) );
//		}
//		else
//		{
//			hp = atkHero.getAtk() - defHero.getDef();
//		}
//		if( hp <= 0  ) hp = 1;
//		
//		if( isGuwu )
//		{
//			hp += (int)(hp*0.2);
//		}
//		
//		
////		命中几率=攻击方命中-被攻击方闪避， 当命中几率≥95%,95%几率命中，反之当命中几率<5，则5%几率命中。
////		（即攻击方最低有5%的命中率，而被攻击方最低也会有5%几率的闪避）
//		int defShanbi = defHero.getHerodod();
////		System.out.println("闪避:"+defShanbi);
//		if( defShanbi < 5 ) defShanbi = 5;
//		int mingzhong = atkHero.getHerotar() - defShanbi + 95;
//		if( mingzhong <5 ) mingzhong =5;
//
//		int r = rand.nextInt(100)+1;
////		System.out.println("命中:"+mingzhong);
////		System.out.println("随机值:"+r);
//		boolean isShanbi = false;
//		//是否闪避
//		if(r > mingzhong)
//		{
//			isShanbi = true;
//			hp = 0;
//			atkEffect = 2;
//		}
//		
////		暴击几率=攻击方的暴击-被攻击方的韧性，当暴击几率≥100时，
////		100%暴击，当暴击几率≤0时，则不暴击
//		int baoji = atkHero.getHerovio() - defHero.getHeroten();
//		if( !isShanbi )
//		{
//    		if( baoji >0 )
//    		{
//    			r = rand.nextInt(100)+1;
//    			if( r <= baoji )
//    			{
////    	总暴击的攻击倍数=(200+（攻击方总破击-被攻击方总抗性）)/100，
////    	小数位保留2位计算伤害。总暴击的攻击倍数最低为2.
////    				System.out.println("baoji");
//    				hp=hp*( (200+(atkHero.getHerodes() - defHero.getHerores() ))/100 );
//    				atkEffect = 1;
//    			}
//    		}
//		}
//		
//		hurt[0] = hp;
//		hurt[1] = atkEffect;
//		
//		return hurt;
//	}
	
	
	/**
	 * 战斗结束处理
	 * @param player
	 * @param result
	 * @param type
	 * @param userid
	 */
	public Result FightResultHandle(Player player, int result ,int type, int userid)
	{
//		Result checkResult = new Result();
//		if( result == 0 )
//		{
//		if(type == 1)
//		{
//			int courseId = userid;
//			DungeonCourseConfig course = DungeonDict.getInstance().getCourseById(courseId);
//			if(course != null)
//			{
////				int dungeonIsFight = 0;
//				int customId = course.getCustomspassid();
//				DungeonCustomConfig custom = DungeonDict.getInstance().getCustomById(customId);
//				int dungeonId = custom.getDungeonId();
//				player.setDungeonState(false);
////				int _courseId = DungeonDict.getInstance().getCustomLastCourseID(customId);
////				if(_courseId == courseId)
////				{
////					dungeonIsFight = 1;
////				}
//
//				player.getPlayerDungeon().dealWithCustomEnd(dungeonId, customId, courseId, player);
//
//				DungeonCourseConfig dco = DungeonDict.getInstance().getCourseById(courseId);
//				
//				if(dco != null)
//				{
////					int rcash = dco.getRewardcash();
//					int rcoin = dco.getRewardcoin();
//					int rexp = dco.getRewardexp();
//					int heroExp = dco.getHeroexp();
////					int reputation = dco.getReputation();
//					int jungong = dco.getJungong();
////					int hun = dco.getHun();
//
////					checkResult.cash = rcash;
//					checkResult.coin = rcoin;
//					checkResult.exp = rexp;
//					checkResult.heroExp = heroExp;
////					checkResult.shengwang = reputation;
//					checkResult.jungong = jungong;
////					checkResult.hun = hun;
//
//					DungeonConfig dc = DungeonDict.getInstance().getDungeonByCourseID(courseId);
////					if(dc != null)
////					{
////						int lastCurseID = DungeonDict.getInstance().getDungeonLastCourseID(dc);
////						if(courseId == lastCurseID && courseId >= player.getPlayerDungeon().courseCurrent(1))
////						{
////							String msg = player.getUserName() + "通过了副本" + dc.getDungeonName() + "。";
////							ChatServiceImpl.getInstance().sendSystemMsg(msg);
////						}
////					}
//				}
//				int currentstart = 3;
//				PlayerDungeonStart pds = new PlayerDungeonStart(1, 1, courseId, currentstart);
//				player.getPlayerDungeon().addStartNum(pds);
////				PlayerDungeonDAO.updateDungeonStart(player);
//
//				int[] dlist = player.getPlayerDungeon().getNextDungeon(dungeonId, customId, courseId);
//				checkResult.doungeonid = dungeonId;
//				checkResult.customid = customId;
//				checkResult.courseid = dlist[2];
//			}
//
//		}
//		}
//		else
//		{
//			//失败
//		}
//		return checkResult;
		return null;
	}
	
	public cResult getNewResult()
	{
		cResult r = new cResult();
		return r;
	}
	
	public class cResult{
		int retValue;
		
		public int getValue()
		{
			return retValue;
		}
	}
	
	public static class UST_ATKLOG_COMBAT_NEWATTACK
	{
	    public int HeroIdAtk;
	    public String heroIdTarget;
	    public int atkType;
	    public String atkEffect;
	    public String hp;
	    public String die;
	    public int sleepTime;
	    public int skillanu;
	    public String skillpng;
	    public int skillrange;
	}
	
	public static class Result {
		public boolean checkScuess = false;;
		public int exp = 0;
		public int coin = 0;
		public int cash = 0;
		public int item = 0;
		public int itemnum = 1;
		public int doungeonid = 0;
		public int customid = 0;
		public int courseid = 0;
		public int heroid = 0;
		public int gold= 0;
		public int heroExp = 0;
		public int jungong=0;
		public int shengwang=0;
		public int hun=0;
		public Vector<Integer> heroList;
	}
}
