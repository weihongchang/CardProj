package com.core.db.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by lidongyuan@pwrd.com on 2015/11/5.
 */
@Entity
@Table(name = "player", indexes = {@Index(columnList = "account,worldid")})
public class PlayerModel {

    private String account;//账号

    private int worldid;//

    private long playerid;


    private String name;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getWorldid() {
        return worldid;
    }

    public void setWorldid(int worldid) {
        this.worldid = worldid;
    }

    @Id
    public long getPlayerid() {//
        return playerid;
    }

    public void setPlayerid(long playerid) {
        this.playerid = playerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
