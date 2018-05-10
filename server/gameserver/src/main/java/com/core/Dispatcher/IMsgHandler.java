package com.core.Dispatcher;

/**
 * 消息逻辑处理器
 * @author weihongchang
 * @date 2015年11月10日
 */
public interface IMsgHandler {
	
	/**
	 * 处理消息
	 * 
	 * @param msg 需要被处理的消息
	 * 
	 */
	public void execute(Runnable msg);

}
