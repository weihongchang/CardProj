package com.core.Dispatcher;

/**
 * 
 * @author weihongchang
 * @date 2015年11月10日
 */
public interface IMsgDispatcher {

	/**
	 * 启动消息处理器
	 */
	public void start();

	/**
	 * 停止消息处理器
	 */
	public void stop();

	/**
	 * 向消息队列投递消息
	 * 
	 * @param msg
	 */
	public void put(Runnable msg);
}