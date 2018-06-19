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
	
	public Formation()
	{
		formation = new int[6];
	}
	
	public Formation(FormationTemplate ft)
	{
		formation = new int[6];
		String[] hlist = ft.heroid.split(";");
		if( hlist != null && hlist.length >0 )
		{
			for(int i=0;i< hlist.length;i++)
			{
				formation[i] = Integer.parseInt(hlist[i]);
			}
		}
	}
	
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
