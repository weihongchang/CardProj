package com.core;

import io.netty.channel.Channel;

/**
 * netty通道，封装了netty原生的<code>Channel</code>，以及其他一些 应用程序自定义的业务逻辑
 * 
 */
public abstract class NettyChannel implements IChannel {

	protected volatile Channel channel;
	
	public Channel getChannel() {
		return channel;
	}
	
	public boolean isOpen() {
		if (channel != null) {
			return channel.isOpen();
		}
		return false;
	}
	
	
	public void write(IMsg msg) {
		if (channel != null) {
			channel.writeAndFlush(msg);
		}
		
	}
	
	public void close() {
		if (channel != null) {
			channel.close();
		}
	}
	
	public boolean closeOnException() {
		return true;
	}
	

	public NettyChannel(Channel channel)
	{
		this.channel = channel;
	}
	
}
