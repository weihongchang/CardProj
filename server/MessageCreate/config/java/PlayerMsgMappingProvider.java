package com.core.Message.Model;

import java.util.HashMap;
import java.util.Map;

import com.core.Message.List.*;
import com.core.Recognizer.IMsgMappingProvider;

public class PlayerMsgMappingProvider implements IMsgMappingProvider {

	public Map<Short, Class<?>> getMsgMapping() {
		Map<Short, Class<?>> map = new HashMap<Short, Class<?>>();
		
		map.put((short)101,CSLoginHandler.class);
		map.put((short)102,Message.SCLogin.class);
		map.put((short)103,CSBuyHeroHandler.class);
		map.put((short)104,Message.SCBuyHero.class);
		map.put((short)105,CSHeroHandler.class);
		map.put((short)106,Message.SCHeroList.class);
		map.put((short)108,Message.SCHeroOne.class);
		map.put((short)109,CSItemHandler.class);
		map.put((short)110,Message.SCItemList.class);
		map.put((short)112,Message.SCItemOne.class);
		map.put((short)113,CSFormationHandler.class);
		map.put((short)114,Message.SCFormation.class);
		map.put((short)115,CSFightHandler.class);
		map.put((short)116,Message.SCFight.class);
		map.put((short)117,CSFightDataHandler.class);
		
		
		return map;
	}

}
