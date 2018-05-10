package com.core.Message;

import com.core.GameMessageChannel;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 客户端发送给服务器的消息的
 * 
 */
public abstract class CGMessage extends BaseNettyMessage<GameMessageChannel> {
	
	@Override
	protected boolean writeImpl()  {
		return false;
	}
}
