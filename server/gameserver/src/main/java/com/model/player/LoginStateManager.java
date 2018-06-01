package com.model.player;

import com.core.IMsg;
import com.core.Message.CGMessage;

/**
 *  玩家状态管理器，管理玩家登陆、退出等大的状态
 * @author weihongchang
 * @date 2015年11月10日
 */
public class LoginStateManager {
	
	private Player player;
	
	/** 当前状态 */
	private volatile LoginStateEnum state;

	public LoginStateManager(Player player) {
		this.player = player;
		this.state = LoginStateEnum.initOk;
	}

	/**
	 * 判断当前状态下是否能处理指定类型的客户端发过来的消息
	 * 
	 * @param messageType
	 * @return
	 */
	public boolean canProcess(Runnable msg) {
		if (!(msg instanceof CGMessage)) {
			return true;
		}

		// 获取消息类型
		short msgType = ((IMsg)msg).getType();

//		switch (player.getState()) {
//			case initOk:
////				if (msgType == MessageType.CG_PUBLIC_PLAYER_LOGIN) {
////					return true;
////				}
//				if( GameStart.recognizer.isInMessageLsit(msgType) )
//				{
//					return true;
//				}
//				break;
//			case authOk:
//				break;
//			case inGame:
//				return true;
//
//			default:
//				break;
//		}

		return false;
	}
	
	
	public LoginStateEnum getState() {
		return state;
	}
}