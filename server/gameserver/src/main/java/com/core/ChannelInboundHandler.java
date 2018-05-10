package com.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.Dispatcher.IMsgDispatcher;
import com.core.Message.ChannelMessage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
@Sharable
public abstract class ChannelInboundHandler<T extends IChannel> extends SimpleChannelInboundHandler<IMsg> {

	protected static final Logger log = LoggerFactory.getLogger("msg");
	private final AttributeKey<IChannel> key = AttributeKey.newInstance("IChannel");
	protected IMsgDispatcher msgDispatcher;
	
	/**
	 * 设置IMsgDispatcher
	 * 
	 */
	public void setMsgDispatcher(IMsgDispatcher msgDispatcher) {
		this.msgDispatcher = msgDispatcher;
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		log.info("channelActive");
		super.channelActive(ctx);
		IChannel channel = createChannel(ctx.channel());
		ctx.attr(key).set(channel);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, IMsg msg)
			throws Exception {
		
		ChannelMessage<IChannel> message = (ChannelMessage<IChannel>)msg;
		
		IChannel nettyChannel = ctx.attr(key).get();
		if (nettyChannel == null) {
			if (log.isWarnEnabled()) {
				log.warn("No sender");
			}
			ctx.close();
			return;
		}
		
		message.setChannel(nettyChannel);
		msgDispatcher.put(message);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
		ctx.close();
	}
	
	/**
	 * 由子类实现的
	 * 
	 * @return
	 */
	abstract protected T createChannel(Channel channel);
}
