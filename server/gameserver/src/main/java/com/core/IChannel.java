package com.core;
public interface IChannel {
	
	/**
	 * 判断当前通道是否处于打开状态
	 * 
	 * @return
	 */
	public boolean isOpen();

	/**
	 * @param msg
	 */
	public void write(IMsg msg);

	/**
	 * 
	 */
	public void close();

	/**
	 * 出现异常时是否关闭连接
	 * 
	 * @return
	 */
	public boolean closeOnException();

}