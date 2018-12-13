package com.qi.web.model;

import com.qi.uno.model.entiy.Card;
import com.qi.uno.model.entiy.Player;

import java.util.ArrayList;

/**
 * @description:
 * @author: qigang
 * @create: 2018-10-15 14:03
 **/
public class DataGameInfo implements Data {
    //房间号
    private String roomid;
    //当前牌堆的牌数
    private Integer cardPileNum;
    //当前弃牌堆的牌数
    private Integer disCardPileNum;
    //上一张出的牌
    private Card prevCard;
    //当前用户的状态
    private Player currentPlayer;
    //应该出牌的用户Id
    private String canPutPlayerId;
    //有时候需要回传的被改变的颜色
    private String truetempcolor;

    private ArrayList<Playermini> playerlist = null;

    private int derction = 0;

    private DataGameInfo(String roomid, Integer cardPileNum, Integer disCardPileNum, Card prevCard, Player currentPlayer,String canPutPlayerId ) {
        this.roomid = roomid;
        this.cardPileNum = cardPileNum;
        this.disCardPileNum = disCardPileNum;
        this.prevCard = prevCard;
        this.currentPlayer = currentPlayer;
        this.canPutPlayerId = canPutPlayerId;
    }

    public DataGameInfo(String roomid, Integer cardPileNum, Integer disCardPileNum, Card prevCard, Player currentPlayer, String canPutPlayerId, String truetempcolor) {
        this.roomid = roomid;
        this.cardPileNum = cardPileNum;
        this.disCardPileNum = disCardPileNum;
        this.prevCard = prevCard;
        this.currentPlayer = currentPlayer;
        this.canPutPlayerId = canPutPlayerId;
        this.truetempcolor = truetempcolor;
    }

    public DataGameInfo(String roomid, Integer cardPileNum, Integer disCardPileNum, Card prevCard, Player currentPlayer, String canPutPlayerId, String truetempcolor,ArrayList<Playermini> playerlist,int derction) {
        this.roomid = roomid;
        this.cardPileNum = cardPileNum;
        this.disCardPileNum = disCardPileNum;
        this.prevCard = prevCard;
        this.currentPlayer = currentPlayer;
        this.canPutPlayerId = canPutPlayerId;
        this.truetempcolor = truetempcolor;
        this.playerlist = playerlist;
        this.derction = derction;
    }

    public static DataGameInfo getInstance(String roomid, Integer cardPileNum, Integer disCardPileNum, Card prevCard, Player currentPlayer, String canPutPlayerId){
        return new DataGameInfo(roomid,cardPileNum,disCardPileNum,prevCard,currentPlayer,canPutPlayerId);
    }

    public static DataGameInfo getInstancewithcolor(String roomid, Integer cardPileNum, Integer disCardPileNum, Card prevCard, Player currentPlayer, String canPutPlayerId,String tempcolor){
        return new DataGameInfo(roomid,cardPileNum,disCardPileNum,prevCard,currentPlayer,canPutPlayerId,tempcolor);
    }

    public static DataGameInfo getInstancewithcolor(String roomid, Integer cardPileNum, Integer disCardPileNum, Card prevCard, Player currentPlayer, String canPutPlayerId,String tempcolor,ArrayList<Playermini> playerlist,int derction){
        return new DataGameInfo(roomid,cardPileNum,disCardPileNum,prevCard,currentPlayer,canPutPlayerId,tempcolor,playerlist,derction);
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }



    public Integer getCardPileNum() {
        return cardPileNum;
    }

    public void setCardPileNum(Integer cardPileNum) {
        this.cardPileNum = cardPileNum;
    }

    public Integer getDisCardPileNum() {
        return disCardPileNum;
    }

    public void setDisCardPileNum(Integer disCardPileNum) {
        this.disCardPileNum = disCardPileNum;
    }

    public Card getPrevCard() {
        return prevCard;
    }

    public void setPrevCard(Card prevCard) {
        this.prevCard = prevCard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getCanPutPlayerId() {
        return canPutPlayerId;
    }

    public void setCanPutPlayerId(String canPutPlayerId) {
        this.canPutPlayerId = canPutPlayerId;
    }


    public String getTruetempcolor() {
        return truetempcolor;
    }

    public void setTruetempcolor(String truetempcolor) {
        this.truetempcolor = truetempcolor;
    }

    public ArrayList<Playermini> getPlayerlist() {
        return playerlist;
    }

    public void setPlayerlist(ArrayList<Playermini> playerlist) {
        this.playerlist = playerlist;
    }

    public int getDerction() {
        return derction;
    }

    public void setDerction(int derction) {
        this.derction = derction;
    }

    @Override
    public String toString() {
        return "DataGameInfo{" +
                "roomid='" + roomid + '\'' +
                ", cardPileNum=" + cardPileNum +
                ", disCardPileNum=" + disCardPileNum +
                ", prevCard=" + prevCard +
                ", currentPlayer=" + currentPlayer +
                ", canPutPlayerId='" + canPutPlayerId + '\'' +
                ", truetempcolor='" + truetempcolor + '\'' +
                ", playerlist=" + playerlist +
                ", derction=" + derction +
                '}';
    }
}
