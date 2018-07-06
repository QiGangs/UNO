package com.qi.uno.model.entiy;

import com.google.common.collect.Lists;
import com.qi.uno.common.CardStatus;
import com.qi.uno.common.RoomStatus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 描述游戏房间
 * @author: qigang
 * @create: 2018-07-05 12:05
 **/
public class Room {
    private Long roomId;
    private int playerNum;
    private ArrayList<Player> players;
    private int direction;
    private LinkedBlockingQueue<Card> cardPile;
    private ArrayList<Card> discardPile;

    public Room(Long roomId, int playerNum) {
        this.roomId = roomId;
        this.playerNum = playerNum;
        this.players = Lists.newArrayListWithCapacity(playerNum);
        this.direction = RoomStatus.DISCARD_DIRECTION_NEXT;
        this.cardPile = new LinkedBlockingQueue<>(CardStatus.CARD_ALL_NUM);
        this.discardPile = Lists.newArrayListWithCapacity(CardStatus.CARD_ALL_NUM);
    }

    public void addPlayer(Player player){

    }

    public void playerExit(Player player){

    }

    public void startGame(){

    }

    public void gameOver(){

    }
}
