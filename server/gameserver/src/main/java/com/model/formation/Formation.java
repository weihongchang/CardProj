package com.model.formation;

/**
 * 阵型
 * @date 	2018年6月6日 下午12:30:11
 * @author 	weihongchang
 *
 */
public class Formation {
	private long playerID;
	private int[] formation;
	
	public long getPlayerID() {
		return playerID;
	}
	public void setPlayerID(long playerID) {
		this.playerID = playerID;
	}
	public int[] getFormation() {
		return formation;
	}
	public void setFormation(int[] formation) {
		this.formation = formation;
	}
	
	
}
