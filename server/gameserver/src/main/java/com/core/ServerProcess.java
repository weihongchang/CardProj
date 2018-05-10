package com.core;

import org.apache.commons.lang.math.NumberUtils;

import com.core.Dispatcher.IMsgDispatcher;
import com.core.Recognizer.IMsgRecognizer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerProcess {
	
	EventLoopGroup parentGroup;
	EventLoopGroup childGroup;
	private final ChannelInboundHandler<? extends IChannel> channelInboundHandler;
	private final IMsgDispatcher msgDispatcher;
	private final IMsgRecognizer messageRecognizer;

	
	public ServerProcess(ChannelInboundHandler<? extends IChannel> _channelInboundHandler,IMsgDispatcher msgDispatcher ,IMsgRecognizer _recognizer)
	{
		channelInboundHandler = _channelInboundHandler;
		messageRecognizer = _recognizer;
		this.msgDispatcher  = msgDispatcher;
		parentGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
		childGroup = new NioEventLoopGroup(NumberUtils.INTEGER_ONE);
		this.channelInboundHandler.setMsgDispatcher(this.msgDispatcher);
	}
	
	public void start()
	{
		msgDispatcher.start();
		ServerBootstrap serverBoot = new ServerBootstrap();
		serverBoot.group(parentGroup, childGroup);
		serverBoot.option(ChannelOption.TCP_NODELAY, true);
		serverBoot.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,1000 );
		serverBoot.option(ChannelOption.SO_KEEPALIVE, true);
		serverBoot.channel( NioServerSocketChannel.class);
		serverBoot.childHandler( new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				// TODO Auto-generated method stub
				ChannelPipeline pipe = ch.pipeline();
				pipe.addLast(channelInboundHandler);
				pipe.addLast("decoder",new MessageDecoder());
				pipe.addLast("encoder",new MessageEncoder());
				 
			}
		});
		serverBoot.bind("127.0.0.1", 8888);
	}

}
