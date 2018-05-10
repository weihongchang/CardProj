package com.player;

/**
 * GameServer上的玩家状态定义，玩家状态统一由主线程维护
 * @author weihongchang
 * @date 2015年11月10日
 */
public enum LoginStateEnum {
	/** 已创建, 默认状态 */
	initOk,
	/** 认证中 */
	inAuth,
	/** 认证完成 */
	authOk, 
	/** 认证失败 */
	authFail, 
	/** 正在加载角色列表 */
	loadingRoleList,
	/** 加载角色列表完成并等待选择 */
	loadRoleListOk,
	/** 正在创建角色 */
	creatingRole,
	/** 创建角色完成 */
	createRoleOk,
	/** 正在选择角色 */
	selectingRole,
	/** 选择角色完成 */
	selectRoleOk,
	/** 真正进入游戏 */
	entryGame,
	/** 游戏中 */
	inGame,
	/** 离开游戏 */
	exitGame,
	/** 正在退出 */
	logouting,
	/** 退出完成 */
	logoutOk
;

	/** 前置状态数组 */
	private LoginStateEnum[] _prevStateArr;

	/**
	 * 枚举参数构造器
	 * 
	 * @param prevStateArr 
	 * 
	 */
	private LoginStateEnum() {
	}

	/**
	 * 类静态构造器
	 * 
	 */
	static {
		inAuth.putPrevStates(initOk, authFail);
		authOk.putPrevStates(inAuth);
		authFail.putPrevStates(inAuth);
		loadingRoleList.putPrevStates(authOk, createRoleOk);
		loadRoleListOk.putPrevStates(loadingRoleList);
		creatingRole.putPrevStates(loadRoleListOk);		
		createRoleOk.putPrevStates(creatingRole);
		selectingRole.putPrevStates(loadRoleListOk);
		selectRoleOk.putPrevStates(selectingRole);
		entryGame.putPrevStates(selectRoleOk);
		inGame.putPrevStates(entryGame);
		exitGame.putPrevStates(inGame);
		logouting.putPrevStates(initOk, authFail, authOk, loadingRoleList, loadRoleListOk, creatingRole, selectingRole, selectRoleOk, entryGame, inGame, exitGame);
		logoutOk.putPrevStates(initOk, authFail, authOk, loadingRoleList, loadRoleListOk, creatingRole, selectingRole, selectRoleOk, entryGame, inGame, exitGame, logouting);
	}

	/**
	 * 设置前置状态
	 * 
	 * @param prevStates 
	 * 
	 */
	private void putPrevStates(LoginStateEnum ... prevStates) {
		this._prevStateArr = prevStates;
	}

	/**
	 * 是否可以进入目标状态
	 * 
	 * @param target
	 * @return
	 * 
	 */
	public boolean canEntry(LoginStateEnum target) {
		if (target == null || 
			target._prevStateArr == null) {
			return false;
		}

		for (LoginStateEnum prev : target._prevStateArr) {
			if (prev == this) {
				return true;
			}
		}

		return false;
	}
}
