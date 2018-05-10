package com.core.Recognizer;
public abstract class MessageType {


	////////////////
	//增加新模块“信令组”要加在最下面。
	//增加已有模块的“信令”可以接着加
	//请在本注释前面添加新消息
	////////////////////////////////////////////////////////////////////////////////////////////////////////


	//
	//  通用消息
	//
	
	//  系统提示消息
	public static final short GC_SYSTEM_MESSAGE = 10001;

	//
	//  角色
	//
	
	//  玩家登陆消息
	public static final short CG_PUBLIC_PLAYER_LOGIN = 10101;
	//  角色列表
	public static final short GC_ROLE_LIST = 10102;
}
