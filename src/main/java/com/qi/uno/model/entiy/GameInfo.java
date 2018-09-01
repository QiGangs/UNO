package com.qi.uno.model.entiy;

import com.google.common.collect.Lists;
import com.qi.uno.common.CardStatus;
import com.qi.uno.common.RoomStatus;
import com.qi.uno.service.PileService;
import com.qi.uno.service.impl.PileServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
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

    //一下两变量貌似没用过
    private ArrayList<Card> canUseCard;    //当前玩家可以使用的牌
    private Card currentPlayerUsedCard;    //当前玩家最后出的牌

    //UNO暂时不考虑
    private boolean isCurrentUNO;    //当前玩家是否UNO
    private boolean isCurrentGetCard;    //当前玩家是否选择摸牌
    private boolean isNextGetCard;    //下一个玩家是否要摸牌

    //超级功能牌变色后临时颜色判别
    private String tempColor;

    //当前玩家是谁，当前出的牌，当前牌是否要执行什么操作，下一个要出牌的玩家，下一张可以出什么牌，是否uno，
    
    
    /** 
    * @Description: 开始游戏，初始化基本信息 //摸牌和获得引导牌
    * @Param: [player] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/7/7 
    */
    public void startGame(Player player){
        for(Player playerx : players){   //发牌
            playerx.setRudge(pileService.getFirstSevenCard(cardPile));
        }
        currentPlayer = player;
        prevCard = cardPile.poll();   //引导牌
        discardPile.add(prevCard);   //引导牌计入弃牌堆
//        canUseCard = getCanUsedCards(); //当前玩家可以出的牌
        balance();
    }
    
    /** 
    * @Description: 描述每局玩家的出牌行为
    * @Param: [] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/7/7 
    */
    public int action(Player player,Card card,Boolean isGetCard){
        if(!player.getPlayerId().equals(currentPlayer.getPlayerId())){
            return -2;
        }
        if(card == null && isGetCard == true){
            getCard(player,1);
            currentPlayer = players.get(getNextPlayer(players,direction,1));
            return -2;
        }
        if(card.getColor() == CardStatus.CRAD_NOT_COLOR){    //草鸡功能牌则直接出
            return putCard(player,card); //出牌
        }else if(prevCard.getType().equals(CardStatus.CARD_TYPE_WILD)){  //上一张为万能牌得分别判断
            if(card.getColor().equals(tempColor)){
                return putCard(player,card);//出牌
            }else {
                return -2;
            }
        }else {
            if(prevCard.getType().equals(CardStatus.CARD_TYPE_FUNC)){    //上一张为其他功能牌则颜色要一致
                if(card.getColor().equals(prevCard.getColor())){
                    return putCard(player,card);//出牌
                }
            }else {                                         //普通牌则判断颜色与大小
                if(card.getColor().equals(prevCard.getColor()) || card.getNum() == prevCard.getNum()){
                    return putCard(player,card);//出牌
                }
            }
        }

        return -1;

    }

    /** 
    * @Description: 每一个玩家出牌之后都要进行的结算函数
     *              //因为结算函数里有切换玩家，所以翻开引导牌时，要调用结算函数，导致第一个玩家本来是1号，
     *              而后切换到2号玩家（第一个玩家因随机开始【未写逻辑】），故无碍）
     *
     *              //其中 变色牌 与 王牌 的变色没有记在结算功能里  ，记录在游戏信息中
    * @Param: [] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/7/7 
    */
    public void balance(){
        if(!prevCard.getType().equals(CardStatus.CARD_TYPE_NUM)){
            if(prevCard.getFunc().equals(CardStatus.CRAD_FUNC_STOP)){
                currentPlayer = players.get(getNextPlayer(players,direction,2));
            }else if(prevCard.getFunc().equals(CardStatus.CRAD_FUNC_REVERSE)){
                direction = (direction == RoomStatus.DISCARD_DIRECTION_NEXT ? RoomStatus.DISCARD_DIRECTION_PREV:RoomStatus.DISCARD_DIRECTION_NEXT);
                currentPlayer = players.get(getNextPlayer(players,direction,1));
            }else if(prevCard.getFunc().equals(CardStatus.CRAD_FUNC_ADD2)){
                currentPlayer = players.get(getNextPlayer(players,direction,1));  //下家摸2张并禁止出牌
                getCard(currentPlayer,2);
                currentPlayer = players.get(getNextPlayer(players,direction,1));
            }else if(prevCard.getFunc().equals(CardStatus.CRAD_FUNC_CHANGE)){
                //变色好烦啊  其实这里好像不需要什么操作.....
                //2018.7.17  这里还是要改，牌只复制了引用，不可将草鸡牌变色记录在牌对象里，会和其他游戏房间混起来
                //2018.8.30  中途接了项目，帮cwq复习专业课，一个半月没看，不知道最近能不能改好。。。
                currentPlayer = players.get(getNextPlayer(players,direction,1));
            }else if(prevCard.getFunc().equals(CardStatus.CRAD_FUNC_TRUMP)){
                currentPlayer = players.get(getNextPlayer(players,direction,1)); //切换到下一用户
                getCard(currentPlayer,4);      //摸四张
                currentPlayer = players.get(getNextPlayer(players,direction,1));  //轮到下下一用户
            }
        }else {
            currentPlayer = players.get(getNextPlayer(players,direction,1));
        }
        return;
    }

    //摸牌的方法
    public void getCard(Player player,int num){
        ArrayList<Card>  tempList = pileService.getSomeCard(cardPile,num);
        //牌不够摸  要洗牌
        if(tempList == null){
            Collections.shuffle(discardPile);
            cardPile.addAll(discardPile);
            discardPile.clear();
            tempList = pileService.getSomeCard(cardPile,num);
        }
        player.getRudge().addAll(tempList);
    }


    //根据牌对象找出牌，然后移除牌    就是出牌    不记得用没用过这个方法
    public int putCard(Player player,Card card){
        int i = pileService.findCardById(player.getRudge(),card.getId());
        if(i != -1){
            Card card1 = player.getRudge().remove(i);
            discardPile.add(card1);
            prevCard = card1;
            balance();
        }
        if(currentPlayer.getRudge().size() == 0){
            return -1;
        }
        return 1;
    }


    //根据规则获得下一个该出牌的用户
    public int getNextPlayer(ArrayList<Player> players,int direction,int num){
        int i;
        for(i = 0;i< players.size();i++){
            if(players.get(i).getPlayerId().equals(currentPlayer.getPlayerId())) {
                break;
            }
        }
        if(direction == RoomStatus.DISCARD_DIRECTION_NEXT){
            i += num;
            i = i%players.size();
        }else {
            i -= num;
            i = (i+players.size())%players.size();
        }
        return i;
    }

    //获取用户当前手牌
    public ArrayList<Card> getPlayerRudge(Long playerId){
        for(int i = 0; i< players.size();i++){
            if(players.get(i).getPlayerId().equals(playerId)){
                return players.get(i).getRudge();
            }
        }
        return null;
    }

//    public ArrayList<Card> getCanUsedCards(){
//        return pileService.getCanUsedCards(prevCard,currentPlayer.getRudge());
//    }
    






















    //以下是构造器以及get，set 最后需要修改

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

    public String getTempColor() {
        return tempColor;
    }

    public void setTempColor(String tempColor) {
        this.tempColor = tempColor;
    }
}
