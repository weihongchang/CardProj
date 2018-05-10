package com.core.Message;

import com.core.IChannel;
import com.core.IMsg;

/**
 * 基于会话的消息
 * 
 * @param <T>
 * 
 */
public interface ChannelMessage<T extends IChannel> extends IMsg {
	/**
	 * 取得此消息的发送者
	 * 
	 * @return
	 */
	public T getChannel();

	/**
	 * 设置该消息的发送者
	 * 
	 * @param sender
	 */
	public void setChannel(T channel);
}
