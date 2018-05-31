package com.core.Message;

import org.java_websocket.WebSocket;

import com.core.IMsg;
import com.core.WsPool;
import com.core.Exception.MessageParseException;
import com.google.protobuf.InvalidProtocolBufferException;

import io.netty.buffer.ByteBuf;

/**
 * IMessage的基本实现
 * 
 */
public abstract class BaseMessage implements IMsg {
	
	protected WebSocket conn;
	/** 与该消息绑定的ByteBuffer,用于read或者write方法的操作对象 * */
	protected ByteBuf buf;
	
	/** 消息的长度 * */
	protected int messageLength;

	/** 消息的类型 * */
	protected short type;

	/** 消息的名称 * */
	protected String typeName;

	protected BaseMessage() {
		
	}

	
	/**
	 * 将当前buf中的数据读取到BaseMessage对象对应的属性中
	 * 
	 * @return true,读取成功;false,读取失败
	 * @throws MessageParseException
	 *             在读取过程发生错误的抛出此异常
	 */
	public boolean read(ByteBuf buff,WebSocket _conn) throws MessageParseException{
		try {
			this.conn = _conn;
			this.buf = buff;
			//获得一个16位的无符号的整数
			this.messageLength = this.buf.readInt();
			this.type = this.buf.readShort();
			return readImpl();
		} catch (Exception e) {
			throw new MessageParseException(" Type:" + this.type, e);
		}
	}
	
	/**
	 * 将该消息的属性写入到当前设置的ByteBuf中,按以下顺序写入:
	 * 
	 * <pre>
	 * 1.消息长度(占位符0,实际的长度当writeImpl写完之后再确定)
	 * 2.消息类型
	 * 3.消息体 
	 * 4.如果第3步成功,则修正消息长度,并返回true 
	 * 5.如果第3步失败,则返回flase
	 * </pre>
	 * 
	 * @return true,写入成功;false,写入失败
	 * @throws MessageParseException
	 *             在写入的过程中发生错误时抛出此异常
	 */
	public boolean write(ByteBuf buff) throws MessageParseException {
		
		try {
			this.buf = buff;
			int index = this.buf.writerIndex();
			this.buf.writeShort(0);
			this.buf.writeShort(getType());
			
			if (!writeImpl()) {
				return false;
			}
			
			this.messageLength = this.buf.readableBytes();
			if (messageLength > MAX_MESSAGE_LENGTH) {
				throw new IllegalArgumentException(
						"The message length is not invalid,value ["
								+ messageLength + "],maybe it's too long?"
								+ this.getTypeName());
			}

			this.buf.setShort(index, (short) messageLength);
			return true;
		} catch (Exception e) {
			throw new MessageParseException(e);
		}
	}
	
	/**
	 * 取得消息的当前长度
	 * 
	 * @return -1,该消息还未与ByteBuf绑定;否则返回ByteBuf的writeIndex
	 */
	public final int getLength() {
		return this.messageLength;
	}
	
	public short getType() {
		return this.type;
	}
	
	public void setType(short _type){
		this.type = _type;
	}

	public String getTypeName() {
		if (typeName == null) {
			return this.getClass().getSimpleName();
		}
		return this.typeName;
	}
	
	protected void writeBytes(byte[] data) {
		buf.writeBytes(data);
	}
	
	protected byte[] readBytes() {
		return buf.readBytes(messageLength - HEADER_SIZE).array();
	}
	
	/**
	 * 运行消息
	 * 
	 */
	public void run() {
		this.execute(WsPool.getPlayerByUser(conn.getRemoteSocketAddress().toString()));
	}
	
	
	/**
	 * 读取buffer中的数据到消息中对应的属性中
	 * 
	 * @return true,读取成功;false,读取失败
	 */
	protected abstract boolean readImpl() throws InvalidProtocolBufferException ;

	/**
	 * 将消息的属性按照消息格式写入到buffer中
	 * 
	 * @return true,写入成功;false,写入失败
	 */
	protected abstract boolean writeImpl() throws InvalidProtocolBufferException ;


	@Override
	public String toString() {
		return "[type=" + type + ", typeName=" + typeName + "]";
	}
	
}
