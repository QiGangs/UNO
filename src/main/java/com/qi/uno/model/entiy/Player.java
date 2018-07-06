package com.qi.uno.model.entiy;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @description: 描述参与游戏者
 * @author: qigang
 * @create: 2018-07-05 12:04
 **/
public class Player {
    private Long playerId;
    private String userId;
    private ArrayList<Card> rudge; //描述用户当前手牌
    private int socre;

    public Player(Long playerId) {
        this.playerId = playerId;
        this.rudge = Lists.newArrayList();
        this.socre = 0;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Card> getRudge() {
        return rudge;
    }

    public void setRudge(ArrayList<Card> rudge) {
        this.rudge = rudge;
    }

    public int getSocre() {
        return socre;
    }

    public void setSocre(int socre) {
        this.socre = socre;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId='" + playerId + '\'' +
                ", userId='" + userId + '\'' +
                ", rudge=" + rudge +
                ", socre=" + socre +
                '}';
    }
}
