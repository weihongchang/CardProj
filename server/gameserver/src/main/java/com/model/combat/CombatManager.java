package com.model.combat;

import java.util.ArrayList;
import java.util.List;

import com.model.dungeon.CourseTemplate;
import com.model.dungeon.DungeonManager;
import com.model.formation.Formation;
import com.model.formation.FormationManager;
import com.model.formation.FormationTemplate;
import com.model.player.Player;

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
    	int customId = 0;
    	
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
		    		customId = Integer.parseInt( course.customspassid);
		    		formationId = Integer.parseInt( course.formationid);
		    	}
		    	
		    	df = FormationManager.getInstance().getFormationById(formationId);
		    	fightNum = FormationManager.getInstance().getFormationFightNum( formationId );
	    	}
	    	
	    		
	    		List<UST_SOLDIERLIST_COMBAT_NEWATTACK> soldierList = new ArrayList<UST_SOLDIERLIST_COMBAT_NEWATTACK>();
	    		List<UST_HERODETAILLIST_COMBAT_NEWATTACK> heroDetailList = new ArrayList<UST_HERODETAILLIST_COMBAT_NEWATTACK>();
	    		
	    		List<CombatNodeData> combatNodeList = null;
	    		
    	    	PvpPlayerData pvpData = null;
    	    	//战斗类型.0pve;1pvp
    	    	int pvptype = 0;
	    		pvpData = new PvpPlayerData(df, fightNum);
    	    	
    	    	if( pvpData != null && pvpData.getFormation() != null )
    	    	{
    	    		combatNodeList = CombatManager.getInstance().getAtkHeroAndInfo(pvptype, thePlayer, targetPlayer, soldierList, heroDetailList,pvpData);
    	    	}
    	    	else
				{
    	    		//没有对手直接胜利
				}
	    	        
	    	        
    	        int resultValue = 0;
    	        cResult result = CombatService.getInstance().getNewResult();
    	        //计算战斗过程
    	        List<UST_ATKLOG_COMBAT_NEWATTACK> atkLog = 
    	        	CombatService.getInstance().getAtkLogPVP(arenaType, combatNodeList, pvpData, thePlayer, targetPlayer, result);
    	        
    	        if( result.getValue() == 1 )
    	        {
    	        	resultValue = 0;
    	        }
    	        else if( result.getValue() == 2 )
    	        {
    	        	resultValue = 1;
    	        }
    	        

	    	        
	    	        
    	        Result checkResult = CombatService.getInstance().FightResultHandle(thePlayer, resultValue,arenaType , userid);
    		    
    	        int maxstart = 5;
    	        int currentstart =3;
    	        int attackType=0;
    	        int dungeonid=0;
    	        int customid=0;
    	        int courseid=0;
    	        int exp=checkResult.exp;
    	        int coin=checkResult.coin;
    	        int gold=100;
    	        
    	        int heroExp=checkResult.heroExp;
    	        int jungong=checkResult.jungong;
    	        int reputation=0;
    	        int hun=0;
    	        
    	        int rewarditemIcon = 0;
		        String rewarditemName ="";
		        int rewarditemtype=0;
		        int rewarditemlevel=0;
		        int rewarditemtrait=0;
		        int rewarditemnum=0;
		        int rewarditematk=0;
		        int rewarditemdef=0;
		        int rewarditemhp=0;
		        int rewarditemfightAtk=0;
		        int rewarditemfightDef=0;
		        String rewarditemDesc="";
		        if( checkResult.item > 0  && checkResult.itemnum>0 )
		        {
		        	ItemConfig ic = ItemDict.getInstance().getItemConfig(checkResult.item);
					if(ic != null)
					{
						rewarditemIcon = ic.icon;
				        rewarditemName =ic.itemname;
				        rewarditemtype=ic.itemttype;
				        rewarditemtrait=ic.trait;
				        rewarditemnum=checkResult.itemnum;
				        rewarditematk=ic.atk;
				        rewarditemdef=ic.def;
				        rewarditemhp=ic.hp;
				        rewarditemfightAtk=ic.fightAtk;
				        rewarditemfightDef=ic.fightDef;
				        
				        if( ic.description.isEmpty() )
				        	rewarditemDesc=ic.getPropertyDesc();
				        else
				        	rewarditemDesc=ic.description;
					}
		        }
		        
		        CombatPacketHandler.sendReplyPacket_combat_newattack(iconSelf, iconTarget, sceneAnuId, scenePngId, HpSelf, HpTarget,
						soldierList, heroDetailList, atkLog, resultValue, maxstart, currentstart, 
						attackType, dungeonid, customid, courseid, exp, coin, gold, 
						rewarditemIcon,rewarditemName,rewarditemtype,rewarditemlevel,
						rewarditemtrait,rewarditemnum,rewarditematk,rewarditemdef,rewarditemhp,
						rewarditemfightAtk,rewarditemfightDef,rewarditemDesc, 
						heroExp, jungong, reputation, hun, thePlayer);
		        

    		    
    		    int Fightid = CombatService.getInstance().getFightingMaxID();
				FightingData fd = new FightingData(Fightid, iconSelf, iconTarget, sceneAnuId, scenePngId, 
						selfName,targetName,HpSelf, HpTarget, resultValue, soldierList, heroDetailList, atkLog);
				CombatService.getInstance().addFightingData(fd);
				
    		    //战斗处理
				combatResultManage(resultValue, arenaType, userid, thePlayer);
	    	}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	    return 0;
	}
	
	
	/**
	 * 获取双方参战人员数据
	 * type 战斗类型.0,pve;1,pvp
	 * @param thePlayer		攻击者
	 * @param targetPlayer	被攻击者
	 * @return
	 */
	public List<CombatNodeData> getAtkHeroAndInfo(int type,Player thePlayer,Player targetPlayer,
			List<UST_SOLDIERLIST_COMBAT_NEWATTACK> soldierList,
			List<UST_HERODETAILLIST_COMBAT_NEWATTACK> uhList,
			PvpPlayerData pvpData )
	{
		List<CombatNodeData> combatNodeList = new ArrayList<CombatNodeData>();
		for( int i =0 ;i<18;i++ )
		{
			combatNodeList.add( new CombatNodeData());
		}
		// 己方阵型
		Formation formation = thePlayer.getHeroFormation().get(0);
		for(int i = 0;i < formation.getFormation().length;i++)
		{
			UST_SOLDIERLIST_COMBAT_NEWATTACK us = new UST_SOLDIERLIST_COMBAT_NEWATTACK();
			// index
			us.boxIndex = i;
			// 英雄id
			us.heroID = formation.getFormation()[i];
			
			combatNodeList.get( i ).boxIndex = i;
			combatNodeList.get( i ).heroID = us.heroID;
			
			soldierList.add(us);
		}

		FormationNew formationTarget = pvpData.getFormation();
		// 对方阵型
		for(int i = 9;i < formationTarget.getFormation().length + 9;i++)
		{
			UST_SOLDIERLIST_COMBAT_NEWATTACK us = new UST_SOLDIERLIST_COMBAT_NEWATTACK();
			// index
			us.boxIndex = i;
			// 英雄id
			us.heroID = formationTarget.getFormation()[i - 9];
			
			combatNodeList.get( i ).boxIndex = i;
			combatNodeList.get( i ).heroID = us.heroID;
			
			soldierList.add(us);
		}

		// 英雄数据
		for(int i = 0;i < soldierList.size();i++)
		{
			UST_SOLDIERLIST_COMBAT_NEWATTACK us = soldierList.get(i);
			if(us.heroID > 0)
			{
				HeroLevelConfig hero = null;
				if(us.boxIndex < 9)
				{
					Hero h = thePlayer.getHero(us.heroID);
					if(h != null)
					{
						hero = HeroDict.getInstance().getHeroLevelConfig(h.getHeroConfigID(), 1);
						if(hero != null)
						{
							UST_HERODETAILLIST_COMBAT_NEWATTACK uh = new UST_HERODETAILLIST_COMBAT_NEWATTACK();
							uh.id = us.boxIndex;
							uh.name = hero.getName();
							uh.hp = h.getHp();
							uh.AnuId = hero.getAnuId();
							uh.PngId = hero.getPngId();
							uh.iconId = hero.getIconId();
							uh.bigiconId = hero.getBigheroiconid();
							uh.modelWidth = hero.getPngwidth();
							uh.modelHeight = hero.getPngheight();
							uh.attackframe = hero.getAttackframe();
							uh.hFlyAnuId = hero.gethFlyAnuId();
							uh.hFlyPngId = hero.gethFlyPngId();
							uh.hFlyPngX = hero.gethFlyPngX();
							uh.hFlyPngY = hero.gethFlyPngY();
							uh.hFlyHeight = hero.gethFlyHeight();
							uh.hFlySpeed = hero.gethFlySpeed();
							uh.hFlyAngle = hero.gethFlyAngle();
							uh.pngHeight = hero.getPngheight();
							uh.pngWidth = hero.getPngwidth();
							uh.heroColor = hero.getColor().getValue();
							uh.soldierNum = hero.getSoldiernum();
							//战斗用的数据
							combatNodeList.get( i ).name = hero.getName();
							combatNodeList.get( i ).AnuId = hero.getAnuId();
							combatNodeList.get( i ).PngId = hero.getPngId();
							combatNodeList.get( i ).iconId = hero.getIconId();
							combatNodeList.get( i ).bigiconId = hero.getBigheroiconid();
							combatNodeList.get( i ).modelWidth = hero.getPngwidth();
							combatNodeList.get( i ).modelHeight = hero.getPngheight();
							combatNodeList.get( i ).attackframe = hero.getAttackframe();
							combatNodeList.get( i ).hFlyAnuId = hero.gethFlyAnuId();
							combatNodeList.get( i ).hFlyPngId = hero.gethFlyPngId();
							combatNodeList.get( i ).hFlyPngX = hero.gethFlyPngX();
							combatNodeList.get( i ).hFlyPngY = hero.gethFlyPngY();
							combatNodeList.get( i ).hFlyHeight = hero.gethFlyHeight();
							combatNodeList.get( i ).hFlySpeed = hero.gethFlySpeed();
							combatNodeList.get( i ).hFlyAngle = hero.gethFlyAngle();
							combatNodeList.get( i ).pngHeight = hero.getPngheight();
							combatNodeList.get( i ).pngWidth = hero.getPngwidth();
							combatNodeList.get( i ).heroColor = hero.getColor().getValue();
							combatNodeList.get( i ).soldierNum = hero.getSoldiernum();
							
							combatNodeList.get( i ).hp = h.getHp();
							combatNodeList.get( i ).def = h.getDef();
							combatNodeList.get( i ).atk = h.getAtk();
							combatNodeList.get( i ).fightAtk = h.getFightAtk();
							combatNodeList.get( i ).fightDef = h.getFightDef();
							combatNodeList.get( i ).skillID = h.getSkill().getSkillID();
							combatNodeList.get( i ).herovio = hero.getHerovio();	
							combatNodeList.get( i ).herodes = hero.getHerodes();	
							combatNodeList.get( i ).herores = hero.getHerores();	
							combatNodeList.get( i ).heroten = hero.getHeroten();	
							combatNodeList.get( i ).herodod = hero.getHerodod();	
							combatNodeList.get( i ).herotar = hero.getHerotar();	
							combatNodeList.get( i ).heroang = hero.getHeroang();

							uhList.add(uh);
						}
					}
				}
				else
				{
					UST_HERODETAILLIST_COMBAT_NEWATTACK uh = new UST_HERODETAILLIST_COMBAT_NEWATTACK();
					HeroLevelExpConfig heroLevel = null;
					HeroData hd = pvpData.getHeroData(us.heroID);
					if( hd != null )
					{
						if(type > 0)
						{// pvp
							hero = HeroDict.getInstance().getHeroLevelConfig(hd.heroType, 1);
							uh.hp = hd.hp;
							combatNodeList.get( i ).hp = hd.hp;
							combatNodeList.get( i ).def = hd.def;
							combatNodeList.get( i ).atk = hd.atk;
							combatNodeList.get( i ).fightAtk = hd.fightAtk;
							combatNodeList.get( i ).fightDef = hd.fightDef;
							combatNodeList.get( i ).skillID = hd.skillID;
						}
						else
						{
							//pve
							int level = 1;
							hero = HeroDict.getInstance().getMonsterConfig(hd.heroType);
							if(hero != null)
							{
								level = hero.getMonsterlevel();
								heroLevel = HeroLevelExpDict.getInstance().getHeroLevelExp(level);
								if( heroLevel != null )
								{
									uh.hp = heroLevel.getHerohp1();
									combatNodeList.get( i ).hp = heroLevel.getHerohp1();
									combatNodeList.get( i ).def = heroLevel.getHerodef1();
									combatNodeList.get( i ).atk = heroLevel.getHeroatk1();
									combatNodeList.get( i ).fightAtk = heroLevel.getHeroFightAtk();
									combatNodeList.get( i ).fightDef = heroLevel.getHeroFightDef();
									combatNodeList.get( i ).skillID =hero.getSkillId();
								}
							}
						}
					}
					if(hero != null)
					{
						uh.id = us.boxIndex;
						uh.name = hero.getName();
						uh.AnuId = hero.getAnuId();
						uh.PngId = hero.getPngId();
						uh.iconId = hero.getIconId();
						uh.bigiconId = hero.getBigheroiconid();
						uh.modelWidth = hero.getPngwidth();
						uh.modelHeight = hero.getPngheight();
						uh.attackframe = hero.getAttackframe();
						uh.hFlyAnuId = hero.gethFlyAnuId();
						uh.hFlyPngId = hero.gethFlyPngId();
						uh.hFlyPngX = hero.gethFlyPngX();
						uh.hFlyPngY = hero.gethFlyPngY();
						uh.hFlyHeight = hero.gethFlyHeight();
						uh.hFlySpeed = hero.gethFlySpeed();
						uh.hFlyAngle = hero.gethFlyAngle();
						uh.pngHeight = hero.getPngheight();
						uh.pngWidth = hero.getPngwidth();
						uh.heroColor = hero.getColor().getValue();
						uh.soldierNum = hero.getSoldiernum();
						
						//战斗用的数据
						combatNodeList.get( i ).name = hero.getName();
						combatNodeList.get( i ).AnuId = hero.getAnuId();
						combatNodeList.get( i ).PngId = hero.getPngId();
						combatNodeList.get( i ).iconId = hero.getIconId();
						combatNodeList.get( i ).bigiconId = hero.getBigheroiconid();
						combatNodeList.get( i ).modelWidth = hero.getPngwidth();
						combatNodeList.get( i ).modelHeight = hero.getPngheight();
						combatNodeList.get( i ).attackframe = hero.getAttackframe();
						combatNodeList.get( i ).hFlyAnuId = hero.gethFlyAnuId();
						combatNodeList.get( i ).hFlyPngId = hero.gethFlyPngId();
						combatNodeList.get( i ).hFlyPngX = hero.gethFlyPngX();
						combatNodeList.get( i ).hFlyPngY = hero.gethFlyPngY();
						combatNodeList.get( i ).hFlyHeight = hero.gethFlyHeight();
						combatNodeList.get( i ).hFlySpeed = hero.gethFlySpeed();
						combatNodeList.get( i ).hFlyAngle = hero.gethFlyAngle();
						combatNodeList.get( i ).pngHeight = hero.getPngheight();
						combatNodeList.get( i ).pngWidth = hero.getPngwidth();
						combatNodeList.get( i ).heroColor = hero.getColor().getValue();
						combatNodeList.get( i ).soldierNum = hero.getSoldiernum();
						
						combatNodeList.get( i ).herovio = hero.getHerovio();	
						combatNodeList.get( i ).herodes = hero.getHerodes();	
						combatNodeList.get( i ).herores = hero.getHerores();	
						combatNodeList.get( i ).heroten = hero.getHeroten();	
						combatNodeList.get( i ).herodod = hero.getHerodod();	
						combatNodeList.get( i ).herotar = hero.getHerotar();	
						combatNodeList.get( i ).heroang = hero.getHeroang();
						
						uhList.add(uh);
					}
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
	public List<UST_ATKLOG_COMBAT_NEWATTACK> getAtkLogPVP(int atkType,List<CombatNodeData> soldierList, PvpPlayerData pvpData,
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
    		
    		List<UST_ATKLOG_COMBAT_NEWATTACK> atkLog = new ArrayList<UST_ATKLOG_COMBAT_NEWATTACK>();
    		
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
            				    		UST_ATKLOG_COMBAT_NEWATTACK ua1 = new UST_ATKLOG_COMBAT_NEWATTACK();
//            				    		ua1.boutNum = boutNum;
            				    		//攻击英雄index
            				    		ua1.HeroIdAtk = atkStep;
            				    		
            				    		//攻击类型；1普通攻击，2技能攻击，3反击
            				    		ua1.atkType = 1;
            				    		
            				    		//被攻击英雄index
            				    		ua1.heroIdTarget = "";
            				    		//攻击效果；0,无效果,1暴击，2闪避
            				    		ua1.atkEffect = "";
            				    		ua1.hp = "";
            				    		ua1.die="";
            				    		
            				    		
//            				    		int[][] hurt = new int[9][2];
            				    		//range 1.普通攻击，2技能攻击
            				    		int range = 1;//rand.nextInt(4)+1;
            				    		int nuqi = nuqilist.get(atkStep);
            				    		SkillConfig skill = null;
            				    		if( nuqi >= 100 )
            				    		{
            				    			range = 2;
            				    			nuqilist.set(atkStep, 0);
            				    			//技能攻击
            				    			int skillID = atkHero.skillID;
            				    			skill = SkillDict.getInstance().getSkillConfig(skillID);
            				    		}
            				    		ua1.skillrange = range;
            				    		//左边是否被攻击
            				    		boolean isLeft = false;
            				    		boolean isGuwu = false;
            				    		if( thePlayer.getQunxiongGuwu() >0 )
            				    		{
            				    			isGuwu = true;
            				    		}
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
                    				    		
                    				    		ua1.heroIdTarget+=heroIdTarget+","+atkEffect+","+hp+","+die+","+newnuqi+",";
                    				    		
            				    			}
            				    		}
            				    		
            				    		//下次攻击时间,秒
            				    		ua1.sleepTime = stepTime;
            				    		
            				    		ua1.skillanu = 11;
            				    		ua1.skillpng = "11";
//            				    		ua1.skillNameAnu = 0;
//            				    		ua1.skillNamePng="";
            				    		if( skill != null )
            				    		{
            				    			ua1.skillanu = skill.getAnuID();
                				    		ua1.skillpng = skill.getPngID();
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
            				    		UST_ATKLOG_COMBAT_NEWATTACK ua1 = new UST_ATKLOG_COMBAT_NEWATTACK();
//            				    		ua1.boutNum = boutNum;
            				    		//攻击英雄index
            				    		ua1.HeroIdAtk = defStep;
            				    		
            				    		
            				    		//攻击类型；1普通攻击，2技能攻击，3反击
            				    		ua1.atkType = 1;
            				    		
            				    		//被攻击英雄index
            				    		ua1.heroIdTarget = "";
            				    		//攻击效果；0,无效果,1暴击，2闪避
            				    		ua1.atkEffect = "";
            				    		ua1.hp = "";
            				    		ua1.die="";
            				    		
            				    		//range 1.普通攻击，2技能攻击
            				    		int range = 1;//rand.nextInt(4)+1;
            				    		int nuqi = nuqilist.get(defStep);
            				    		SkillConfig skill = null;
            				    		if( nuqi >= 100 )
            				    		{
            				    			range = 2;
            				    			nuqilist.set(defStep, 0);
            				    			
            				    			//技能攻击
            				    			int skillID = atkHero.skillID;
            				    			skill = SkillDict.getInstance().getSkillConfig(skillID);
            				    		}
            				    		ua1.skillrange = range;
            				    		
            				    		//左边是否被攻击
            				    		boolean isLeft = true;
            				    		boolean isGuwu = false;
            				    		if( targetPlayer != null  )
            				    		{
                				    		if( targetPlayer.getQunxiongGuwu() >0 )
                				    		{
                				    			isGuwu = true;
                				    		}
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
                    				    		
                    				    		ua1.heroIdTarget+=heroIdTarget+","+atkEffect+","+hp+","+die+","+newnuqi+",";
            				    			}
            				    		}
            				    		
            				    		//下次攻击时间,秒
            				    		ua1.sleepTime = stepTime;
            				    		
            				    		ua1.skillanu = 11;
            				    		ua1.skillpng = "11";
//            				    		ua1.skillNameAnu = 0;
//            				    		ua1.skillNamePng="";
            				    		if( skill != null )
            				    		{
            				    			ua1.skillanu = skill.getAnuID();
                				    		ua1.skillpng = skill.getPngID();
//                				    		ua1.skillNameAnu = skill.getSkillnameanu();
//                				    		ua1.skillNamePng=skill.getSkillnamepng();
//                				    		ua1.skillAttackStep = skill.getAtkframe();
            				    		}
//            				    		ua1.nuqi =  nuqilist.get(defStep);
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
}
