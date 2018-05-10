package com.core.Recognizer;

import java.util.Map;

public interface IMsgMappingProvider {
	/**
	 * 获得消息类型和消息类的映射关系
	 * 
	 * @return
	 */
	public Map<Short, Class<?>> getMsgMapping();
}	