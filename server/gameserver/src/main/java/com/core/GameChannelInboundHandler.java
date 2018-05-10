package com.core;

import com.player.Player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;


public class GameChannelInboundHandler extends ChannelInboundHandler<GameMessageChannel>{

	@Override
	protected GameMessageChannel createChannel(Channel channel) {
		
		GameMessageChannel msgChannel = new GameMessageChannel(channel);
		Player _player = new Player(msgChannel);
		_player.init();
		return msgChannel;
	}

}