package com.core;



import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;

import com.core.Exception.MessageParseException;
import com.model.player.Player;

import io.netty.buffer.ByteBuf;

public interface IMsg extends Runnable {

	/** 最大消息长度为64K，可能超过这个长度的信息，需要自己打包分多次发送* */
	public static final int MAX_MESSAGE_LENGTH = 65535;

	/** 从客户端接收的消息估计长度,64字节,对于从客户端接收的数据来说，都是简单的命令，很少超过64B * */
	public static final int DECODE_MESSAGE_LENGTH = 64;

	/** 发送给客户端的消息长度,一般也少于512 * */
	public static final int ENCODE_MESSAGE_LENGTH = 512;

	/** 消息头的长度的字节数 {@value} */
	public static final int HEADER_LEN_BYTES = 4;

	/** 消息头的类型字节数 {@value} */
	public static final int HEADER_TYPE_BYTES = 2;

	/** 消息头的长度,4字节,length+type,{@value} */
	public static final int HEADER_SIZE = HEADER_LEN_BYTES + HEADER_TYPE_BYTES;

	/** 最小命令长度，只有消息头 * */
	public static final int MIN_MESSAGE_LENGTH = HEADER_SIZE;
	
	
	public WebSocket getConn();

	public void setConn(WebSocket conn);

	/**
	 * 从由setBuffer设置的消息数据缓存中解析出该消息的数据
	 * 
	 * @return true,解析成功;false,解析失败
	 * @throws MessageParseException
	 */
	public boolean read(ByteBuf buff,WebSocket conn) throws MessageParseException ;
	
	/**
	 * 从由setBuffer设置的消息数据缓存中解析出该消息的数据
	 * 
	 * @return true,解析成功;false,解析失败
	 * @throws MessageParseException
	 */
	public boolean read(ByteBuffer buff) throws MessageParseException ;

	/**
	 * 将该消息的数据写入到由setBuffer设置的消息数据缓存
	 * 
	 * @return true,写入成功;false,写入失败
	 * @throws MessageParseException
	 */
	public boolean write(ByteBuf buff) throws MessageParseException ;
	
	/**
	 * 将该消息的数据写入到由setBuffer设置的消息数据缓存
	 * 
	 * @return true,写入成功;false,写入失败
	 * @throws MessageParseException
	 */
	public boolean write(ByteBuffer buff) throws MessageParseException ;
	
	/**
	 * 取得该消息的类型
	 * 
	 * @return
	 */
	public short getType();

	/**
	 * 取得该消息的名称
	 * 
	 * @return
	 */
	public String getTypeName();

	/**
	 * 取得该消息的长度
	 * 
	 * @return
	 */
	public int getLength();

	/**
	 * 执行消息的处理
	 */
	public abstract void execute(Player player);
}
