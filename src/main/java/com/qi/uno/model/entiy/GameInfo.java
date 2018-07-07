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
    private ArrayList<Player> players;  //玩家列表
    private PileService pileService;   //牌堆服务
    private LinkedBlockingQueue<Card> cardPile;   //牌堆
    private ArrayList<Card> discardPile; //弃牌堆

    private int direction;      //出牌方向
    private Player currentPlayer;   //当前出牌的玩家
    private Card prevCard;        //上一个玩家出的牌
    private ArrayList<Card> canUseCard;    //当前玩家可以使用的牌
    private Card currentPlayerUsedCard;    //当前玩家最后出的牌

    private boolean isCurrentUNO;    //当前玩家是否UNO
    private boolean isCurrentGetCard;    //当前玩家是否选择摸牌
    private boolean isNextGetCard;    //下一个玩家是否要摸牌

    //当前玩家是谁，当前出的牌，当前牌是否要执行什么操作，下一个要出牌的玩家，下一张可以出什么牌，是否uno，
    
    
    /** 
    * @Description: 开始游戏，初始化基本信息 //摸牌和获得引导牌
    * @Param: [player] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/7/7 
    */
    public void startGame(Player player){
        currentPlayer = player;
        for(Player playerx : players){   //发牌
            playerx.setRudge(pileService.getFirstSevenCard(cardPile));
        }
        prevCard = cardPile.poll();   //引导牌
        canUseCard = getCanUsedCards(); //当前玩家可以出的牌
    }
    
    /** 
    * @Description: 描述每局玩家的出牌行为
    * @Param: [] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/7/7 
    */
    public void action(Card card,Boolean isGetCard){
        
    }

    /** 
    * @Description: 每一个玩家出牌之后都要进行的结算函数 
    * @Param: [] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/7/7 
    */
    public void balance(){
        
    }

    public ArrayList<Card> getCanUsedCards(){
        return pileService.getCanUsedCards(prevCard,currentPlayer.getRudge());
    }
    
    

    public GameInfo(ArrayList<Player> players){
        this.players = players;
        this.pileService = new PileServiceImpl();
        this.direction = RoomStatus.DISCARD_DIRECTION_NEXT;
        this.cardPile = pileService.getRuffleCard();
        this.discardPile = Lists.newArrayListWithCapacity(CardStatus.CARD_ALL_NUM);
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Card getPrevCard() {
        return prevCard;
    }

    public void setPrevCard(Card prevCard) {
        this.prevCard = prevCard;
    }

    public ArrayList<Card> getCanUseCard() {
        return canUseCard;
    }

    public void setCanUseCard(ArrayList<Card> canUseCard) {
        this.canUseCard = canUseCard;
    }

    public Card getCurrentPlayerUsedCard() {
        return currentPlayerUsedCard;
    }

    public void setCurrentPlayerUsedCard(Card currentPlayerUsedCard) {
        this.currentPlayerUsedCard = currentPlayerUsedCard;
    }

    public boolean isCurrentUNO() {
        return isCurrentUNO;
    }

    public void setCurrentUNO(boolean currentUNO) {
        isCurrentUNO = currentUNO;
    }

    public boolean isCurrentGetCard() {
        return isCurrentGetCard;
    }

    public void setCurrentGetCard(boolean currentGetCard) {
        isCurrentGetCard = currentGetCard;
    }

    public boolean isNextGetCard() {
        return isNextGetCard;
    }

    public void setNextGetCard(boolean nextGetCard) {
        isNextGetCard = nextGetCard;
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
