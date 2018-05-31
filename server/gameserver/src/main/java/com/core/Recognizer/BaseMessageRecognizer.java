package com.core.Recognizer;

import org.apache.commons.lang.math.NumberUtils;

import com.core.IMsg;
import com.core.Exception.MessageParseException;

import io.netty.buffer.ByteBuf;

/**
 * 基础的消息识别器
 * 
 * 
 */
public abstract class BaseMessageRecognizer implements IMsgRecognizer {
	
	/**
	 * 识别buf中消息头的长度
	 * @param buff
	 * @return -1,无法取得消息头的长度;>-1,消息头中的消息长度
	 * @throws MessageParseException
	 */
	public int recognizePacketLen(ByteBuf buff) throws MessageParseException{
		// 消息头还未读到,返回null
				
		if (buff.readableBytes() < IMsg.MIN_MESSAGE_LENGTH) {
			return NumberUtils.INTEGER_MINUS_ONE;
		}
		try {
			buff.markReaderIndex();
				
	        int length = buff.readInt();
	        return length;
		} catch (Exception e) {
			 throw new MessageParseException(e);
		} finally{
			 buff.resetReaderIndex();
		}
	}
	


	/**
	 * 从buf的当前位置读取消息头,并解析消息的类型,如果找到消息的类型,则返回消息实例,否则返回null
	 * 
	 * @param buf
	 * @return 如果buf中数据足够识别出一个消息，并且该消息的数据已经全部到达,则返回对应的消息实例;否则返回null
	 * @throws MessageParseException
	 */
	public IMsg recognize(ByteBuf buff) throws MessageParseException{
		// 消息头还未读到,返回null
		if (buff.readableBytes() < IMsg.MIN_MESSAGE_LENGTH) {
			return null;
		}
		// 读取前4字节
		try {
			buff.markReaderIndex();
			int len = buff.readInt();
			short type = buff.readShort();
			return createMessage(type);
		} catch (Exception e) {
			throw new MessageParseException(e);
		} finally{
			buff.resetReaderIndex();
		}
	}
	
	
	/**
	 * 根据type构建消息的实例
	 * 
	 * @param type
	 *            消息的类型
	 * @return 消息实例
	 * @throws MessageParseException
	 *            没有与type相匹配的消息类型时,会抛出此异常
	 */
	public abstract IMsg createMessage(short type) throws MessageParseException ;
	
}