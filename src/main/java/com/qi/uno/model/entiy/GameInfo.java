package com.qi.uno.model.entiy;

import com.google.common.collect.Lists;
import com.qi.uno.common.CardStatus;
import com.qi.uno.common.RoomStatus;
import com.qi.uno.service.PileService;
import com.qi.uno.service.impl.PileServiceImpl;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 描述房间内的每局游戏信息
 * @author: qigang
 * @create: 2018-07-06 17:33
 **/
public class GameInfo {
    private PileService pileService;   //牌堆服务
    private int direction;      //出牌方向
    private LinkedBlockingQueue<Card> cardPile;   //牌堆
    private ArrayList<Card> discardPile; //弃牌堆

    //当前玩家是谁，当前出的牌，当前牌是否要执行什么操作，下一个要出牌的玩家，下一张可以出什么牌，是否uno，

    public GameInfo(){
        this.pileService = new PileServiceImpl();
        this.direction = RoomStatus.DISCARD_DIRECTION_NEXT;
        this.cardPile = pileService.getRuffleCard();
        this.discardPile = Lists.newArrayListWithCapacity(CardStatus.CARD_ALL_NUM);
    }

    public PileService getPileService() {
        return pileService;
    }

    public void setPileService(PileService pileService) {
        this.pileService = pileService;
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
}
