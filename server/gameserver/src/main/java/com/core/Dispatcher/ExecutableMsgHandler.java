package com.core.Dispatcher;

/**
 * 
 * 可自执行的消息处理器
 * 
 */
public class ExecutableMsgHandler implements IMsgHandler {

	public void execute(Runnable msg) {
		msg.run();
	}

}
