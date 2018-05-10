package com.core.Message;

import org.java_websocket.WebSocket;

import com.core.NettyChannel;

/**
 * 带有消息发送者的消息基类
 * 
 * @param <T>
 */
public abstract class BaseNettyMessage<T extends NettyChannel> extends BaseMessage implements ChannelMessage<T>{

	private T channel;
	

	public T getChannel() {
		return channel;
	}

	public void setChannel(T channel) {
		this.channel = channel;
	}
	
}