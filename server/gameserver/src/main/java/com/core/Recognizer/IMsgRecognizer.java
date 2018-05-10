package com.core.Recognizer;

import com.core.IMsg;
import com.core.Exception.MessageParseException;

import io.netty.buffer.ByteBuf;

public interface IMsgRecognizer {
	
	
	/**
	 * 判断buff中的数据是哪种消息类型
	 * 
	 * @param buff
	 *            数据缓存区
	 * @return buf中的数据足够识别一个消息的类型,并且数据已经全部到达时返回识别出的消息实例;
	 *         没有足够的数据识别出一个消息，或者数据还未全部到达时返回null
	 * @throws MessageParseException
	 */
	public IMsg recognize(ByteBuf buff) throws MessageParseException;
	
	
	/**
	 * 识别buf中消息头的长度
	 * @param buff
	 * @return -1,无法取得消息头的长度;>-1,消息头中的消息长度
	 * @throws MessageParseException
	 */
	public int recognizePacketLen(ByteBuf buff) throws MessageParseException;
	
}