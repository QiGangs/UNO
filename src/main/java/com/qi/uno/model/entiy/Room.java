package com.qi.uno.model.entiy;

import com.google.common.collect.Lists;
import com.qi.uno.common.CardStatus;
import com.qi.uno.common.RoomStatus;
import com.qi.uno.service.PileService;
import com.qi.uno.service.impl.PileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 描述游戏房间
 * @author: qigang
 * @create: 2018-07-05 12:05
 **/
public class Room {
    private PileService pileService;   //牌堆服务
    private Long roomId;     //房间ID
    private int playerNum;    //玩家个数
    private ArrayList<Player> players;  //玩家列表
    private int direction;      //出牌方向
    private LinkedBlockingQueue<Card> cardPile;   //牌堆
    private ArrayList<Card> discardPile; //弃牌堆
    private Long mainPlayerId;   //房主ID


    public Room(Long roomId, int playerNum,Player mainPlayer) {
        pileService = new PileServiceImpl();
        this.roomId = roomId;
        this.playerNum = playerNum;
        this.players = Lists.newArrayListWithCapacity(playerNum);
        this.direction = RoomStatus.DISCARD_DIRECTION_NEXT;
        this.cardPile = pileService.getRuffleCard();
        this.discardPile = Lists.newArrayListWithCapacity(CardStatus.CARD_ALL_NUM);
        this.mainPlayerId = mainPlayer.getPlayerId();
        addPlayer(mainPlayer);
    }

    //房间行为逻辑
    public void addPlayer(Player player){

    }

    public void playerExit(Player player){

    }

    public void startGame(){

    }

    public void gameOver(){

    }



    //get set
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public LinkedBlockingQueue<Card> getCardPile() {
        return cardPile;
    }

    public void setCardPile(LinkedBlockingQueue<Card> cardPile) {
        this.cardPile = cardPile;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(ArrayList<Card> discardPile) {
        this.discardPile = discardPile;
    }

    public Long getMainPlayerId() {
        return mainPlayerId;
    }

    public void setMainPlayerId(Long mainPlayerId) {
        this.mainPlayerId = mainPlayerId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", playerNum=" + playerNum +
                ", players=" + players +
                ", direction=" + direction +
                ", cardPile=" + cardPile +
                ", discardPile=" + discardPile +
                ", mainPlayerId=" + mainPlayerId +
                '}';
    }
}
