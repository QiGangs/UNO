package com.qi.web.model;

import com.qi.uno.model.entiy.Card;
import com.qi.uno.model.entiy.Player;

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

    private DataGameInfo(String roomid, Integer cardPileNum, Integer disCardPileNum, Card prevCard, Player currentPlayer,String canPutPlayerId ) {
        this.roomid = roomid;
        this.cardPileNum = cardPileNum;
        this.disCardPileNum = disCardPileNum;
        this.prevCard = prevCard;
        this.currentPlayer = currentPlayer;
        this.canPutPlayerId = canPutPlayerId;
    }

    public static DataGameInfo getInstance(String roomid, Integer cardPileNum, Integer disCardPileNum, Card prevCard, Player currentPlayer,String canPutPlayerId){
        return new DataGameInfo(roomid,cardPileNum,disCardPileNum,prevCard,currentPlayer,canPutPlayerId);
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

    @Override
    public String toString() {
        return "DataGameInfo{" +
                "roomid='" + roomid + '\'' +
                ", cardPileNum=" + cardPileNum +
                ", disCardPileNum=" + disCardPileNum +
                ", prevCard=" + prevCard +
                ", currentPlayer=" + currentPlayer +
                ", canPutPlayerId='" + canPutPlayerId + '\'' +
                '}';
    }
}
