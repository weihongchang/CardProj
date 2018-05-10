package com.core;

import com.player.Player;

import io.netty.channel.Channel;

/**
 * 与 GameServer 连接的客户端的会话信息
 * 
 */
public class GameMessageChannel extends NettyChannel {

	/** 当前会话绑定的玩家对象，登录验证成功之后实例化 */
	private Player player;

	public GameMessageChannel(Channel c) {
		super(c);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void write(IMsg msg) {
		if (this.channel == null || !this.channel.isOpen()) {
			return;
		}
		final Channel _channel = this.channel;
		if (_channel != null) {
			_channel.writeAndFlush(msg);
		}
	}
}