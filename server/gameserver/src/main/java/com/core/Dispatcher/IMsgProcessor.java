package com.core.Dispatcher;

/**
 * 消息处理器接口
 * @author weihongchang
 * @date 2015年11月10日
 */
public interface IMsgProcessor {

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

	/**
	 * 判断队列是否已经达到上限了
	 * @return
	 */
	public boolean isFull();
}