package com.core.Recognizer;

import com.core.Exception.MessageParseException;
import com.core.IMsg;
import com.core.Message.Model.PlayerMsgMappingProvider;

import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.java_websocket.WebSocket;

public class GameMesageRecognizer extends BaseMessageRecognizer  {

	private Map<Short, Class<?>> msgs = new HashMap<Short, Class<?>>();

	public GameMesageRecognizer() {
		this.init();
	}

	public void init() {
		// 消息映射提供者数组
		IMsgMappingProvider[] providerArr = {
				new PlayerMsgMappingProvider()
		};

		for (IMsgMappingProvider provider : providerArr) {
			// 注册消息提供者
			this.registerMsgMapping(provider);
		}
	}

	/**
	 * 注册消息号和消息类的映射
	 * 
	 * @param mappingProvider
	 */
	private void registerMsgMapping(IMsgMappingProvider mappingProvider) {
		msgs.putAll(mappingProvider.getMsgMapping());
	}
	
	/**
	 * 
	 */
	public IMsg createMessage(short type) throws MessageParseException {
		Class<?> clazz = msgs.get(type);
		if (clazz == null) {
			throw new MessageParseException("Unknow msgType:" + type);
		} else {
			try {
				IMsg msg = (IMsg) clazz.newInstance();
				return msg;
			} catch (Exception e) {
				throw new MessageParseException(
						"Message Newinstance Failed，msgType:" + type, e);
			}
		}
	}
	
	public short getTypeForMsg(Class<?> c){
		
		for( Entry<Short, Class<?>> entry :  msgs.entrySet() )
		{
			if(entry.getValue().getName() == c.getName())
				return entry.getKey();
		}
		return 0;
	}
	
	public boolean isInMessageLsit(short mType)
	{
		if(msgs.containsKey(mType))
			return true;
		return false;
	}


}
