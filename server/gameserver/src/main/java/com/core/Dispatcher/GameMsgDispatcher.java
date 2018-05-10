package com.core.Dispatcher;

/**
 * 主消息处理器
 * @author weihongchang
 * @date 2015年11月10日 
 * @param <T>
 */
public class GameMsgDispatcher<T extends IMsgProcessor> implements IMsgDispatcher {
	
	/** 主消息处理器,用于处理一些状态全局共享的数据 */
	private final T mainProcessor;

	/** 停止处理标识 */
	private volatile boolean stop = false;
	private volatile boolean isStarted = false;
	
	/**
	 * 类参数构造器
	 *  
	 * @param mainProcessor 
	 * 
	 */
	public GameMsgDispatcher(T mainProcessor) {
		this.mainProcessor = mainProcessor;
	}
	
	public T getMainProcessor() {
		return mainProcessor;
	}
	

	/**
	 * 将待处理消息放入{@link IMsgProcessor}消息处理队列中
	 */
	public void put(Runnable msg) {
		if (!stop) {
			this.mainProcessor.put(msg);
		}
	}

	/**
	 * 开始处理
	 */
	public void start() {
		if (isStarted) {
			return;
		}
		isStarted = true;
		stop = false;
		this.mainProcessor.start();
	}

	/**
	 * 停止处理
	 */
	public void stop() {
		stop = true;
		this.mainProcessor.stop();
	}

}
