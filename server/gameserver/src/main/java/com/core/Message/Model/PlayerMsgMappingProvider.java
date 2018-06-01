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
		map.put((short)106,Message.SCServerList.class);
		map.put((short)107,CSMsgHandler.class);
		map.put((short)108,Message.SCMsg.class);
		
		
		return map;
	}

}
