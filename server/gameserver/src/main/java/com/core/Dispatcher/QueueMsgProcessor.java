package com.core.Dispatcher;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.Exception.CommonErrorLogInfo;
import com.core.Exception.ErrorsUtil;

public class QueueMsgProcessor implements Runnable, IMsgProcessor {
	
	/** 内置线程默认名称 */
	private static final String INNER_THREAD_DEFAULT_NAME = "MessageProcessor Thread";

	private static final Logger logger = LoggerFactory.getLogger("messageQueue");
	
	/** 消息队列 * */
	private final BlockingQueue<Runnable> queue;

	/** 消息处理器 * */
	private final IMsgHandler handler;

	/** 消息处理线程 */
	private volatile Thread messageProcessorThread;

	/** 运行的线程id * */
	private long threadId;

	/** 是否停止 */
	private volatile boolean stop = false;

	/** 内置线程名称 */
	private String _innerThreadName;


	/**
	 * 类参数构造器
	 * 
	 * @param messageHandler
	 * @param innerThreadName 内置线程名称
	 * 
	 */
	public QueueMsgProcessor(IMsgHandler messageHandler, String innerThreadName) {
		this.queue = new LinkedBlockingQueue<Runnable>();
		this.handler = messageHandler;
		this._innerThreadName = innerThreadName;
	}

	/**
	 * 类默认构造器
	 * 
	 * @see ExecutableMsgHandler 
	 * 
	 */
	public QueueMsgProcessor() {
		this(new ExecutableMsgHandler(), INNER_THREAD_DEFAULT_NAME);
	}

	/**
	 * 类参数构造器
	 * 
	 * @param innerThreadName
	 * 
	 * @see ExecutableMsgHandler 
	 * 
	 */
	public QueueMsgProcessor(String innerThreadName) {
		this(new ExecutableMsgHandler(), innerThreadName);
	}

	public void put(Runnable msg) {
		if (this.stop) {
			return;
		}
		try {
			queue.put(msg);
			if (logger.isDebugEnabled()) {
				logger.debug("put queue size:" + queue.size());
			}
		} catch (InterruptedException e) {
			if (logger.isErrorEnabled()) {
				logger.error(CommonErrorLogInfo.THRAD_ERR_INTERRUPTED, e);
			}
		}
	}

	/**
	 * 主处理函数，从消息队列中阻塞取出消息，然后处理
	 */
	public void run() {
		threadId = Thread.currentThread().getId();
		try {
			while (!stop) {
				try {
					process(queue.take());
					if (logger.isDebugEnabled()) {
						logger.debug("run queue size:" + queue.size());
					}
				} catch (InterruptedException e) {
					if (logger.isWarnEnabled()) {
						logger.warn("[#CORE.QueueMessageProcessor.run] [Stop process]");
					}
					break;
				} catch (Exception ex) {
					logger.error(CommonErrorLogInfo.MSG_PRO_ERR_EXP, ex);
				}
			}
		} finally {
		}
	}

	/**
	 * 处理具体的消息，每个消息有自己的参数和来源,如果在处理消息的过程中发生异常,则马上将此消息的发送者断掉
	 * 
	 * @param msg
	 */
	public void process(Runnable msg) {
		if (msg == null) {
			if (logger.isWarnEnabled()) {
				logger.warn("[#CORE.QueueMessageProcessor.process] ["
						+ CommonErrorLogInfo.MSG_PRO_ERR_NULL_MSG + "]");
			}
			return;
		}
		try {
			this.handler.execute(msg);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(ErrorsUtil.error("Error",
						"#.QueueMessageProcessor.process", "param"), e);
			}
		} 
	}

	public IMsgHandler getHandler() {
		return handler;
	}

	public long getThreadId() {
		return threadId;
	}

	/**
	 * 取得未处理消息队列的长度
	 * 
	 * @return
	 */
	public int getQueueLength() {
		return queue.size();
	}

	public synchronized void stop() {
		logger.info("Message processor thread " + this.messageProcessorThread
				+ " stopping ...");
		this.stop = true;
		if (this.messageProcessorThread != null) {
			this.messageProcessorThread.interrupt();
		}
		logger.info("Message processor thread " + this.messageProcessorThread
				+ " finish");
		this.messageProcessorThread = null;
		this.queue.clear();
	}

	/**
	 * 开始消息处理
	 */
	public synchronized void start() {
		this.queue.clear();
		stop = false;
		if (this.messageProcessorThread != null) {
			throw new IllegalStateException("The thread has already run"
					+ this.messageProcessorThread);
		}

		if (this._innerThreadName == null || 
			this._innerThreadName.isEmpty()) {
			this._innerThreadName = INNER_THREAD_DEFAULT_NAME;
		}

		this.messageProcessorThread = new Thread(this, this._innerThreadName);
		this.messageProcessorThread.start();

		logger.info("Message processor thread started ["
				+ this.messageProcessorThread + "]");
	}

	/**
	 * 达到5000上限时认为满了
	 */
	public boolean isFull() {
		return this.queue.size() > 5000;
	}

	public boolean isStop() {
		return stop;
	}
}

