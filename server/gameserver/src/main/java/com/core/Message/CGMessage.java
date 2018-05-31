package com.core.Message;

/**
 * 客户端发送给服务器的消息的
 * 
 */
public abstract class CGMessage extends BaseMessage {
	
	@Override
	protected boolean writeImpl()  {
		return false;
	}
}
