package com.core.Dispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.GameMessageChannel;
import com.core.IMsg;
import com.core.Message.CGMessage;
import com.player.Player;

/**
 * 游戏服务器消息处理器
 * @author weihongchang
 * @date 2015年11月10日
 */
public final class MainMsgProcessor implements IMsgProcessor {
	/** 主消息处理器实例 */
	private static volatile MainMsgProcessor _instance = null;

	/** 日志对象 */
	protected final Logger LOG = LoggerFactory.getLogger("msg");
	/** 内置消息处理器 */
	private QueueMsgProcessor _innerMsgProc = null;

	/**
	 * 类默认构造器
	 * 
	 */
	private MainMsgProcessor() {
		this._innerMsgProc = new QueueMsgProcessor(new ExecutableMsgHandler(),
				"Game-MainMsgProcessor");
	}

	/**
	 * 获取单例对象
	 * 
	 * @return
	 * 
	 */
	public static MainMsgProcessor theInstance() {
		if (_instance == null) {
			synchronized (MainMsgProcessor.class) {
				if (_instance == null) {
					_instance = new MainMsgProcessor();
				}
			}
		}

		return _instance;
	}

	public boolean isFull() {
		return _innerMsgProc.isFull();
	}

	/**
	 * <pre>
	 * 1、服务器内部消息、玩家不属于任何场景时发送的消息，单独一个消息队列进行处理
	 * 2、玩家在场景中发送过来的消息，添加到玩家的消息队列中，在场景的线程中进行处理
	 * </pre>
	 */
	public void put(Runnable msg) {
		if (msg instanceof CGMessage) {
			GameMessageChannel channel = ((CGMessage) msg).getChannel();
			if (channel == null) {
				return;
			}
			Player player = channel.getPlayer();
			if (player == null) {
				LOG.error("player not found. msg:" + msg);
				return;
			}

			LOG.info("[[ Receive ]] " + msg + player);

			if (!player.getStateManager().canProcess(msg)) {
				return;
			}
			_innerMsgProc.put(msg);
		} else {
			if (LOG.isDebugEnabled()) {
				LOG.info("[[ Receive ]] " + msg);
			}

			// 执行消息
			this.execute(msg);
		}
	}

	public void start() {
		this._innerMsgProc.start();
	}

	public void stop() {
		this._innerMsgProc.stop();
	}

	/**
	 * 获得主消息处理线程Id
	 * 
	 * @return
	 */
	public long getThreadId() {
		return this._innerMsgProc.getThreadId();
	}

	/**
	 * @return
	 */
	public boolean isStop() {
		return this._innerMsgProc.isStop();
	}

	/**
	 * 执行消息
	 * 
	 * @param msg
	 * 
	 */
	public void execute(IMsg msg) {
		if (msg == null) {
			return;
		} else {
			this._innerMsgProc.put(msg);
		}
	}

	/**
	 * 执行 Runnable
	 * 
	 * @param r
	 * 
	 */
	public void execute(Runnable r) {
		if (r == null) {
			return;
		} else {
			this._innerMsgProc.put(r);
		}
	}
}
