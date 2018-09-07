package com.qi.uno.model.entiy;

import com.google.common.collect.Lists;
import com.qi.uno.common.CardStatus;
import com.qi.uno.common.RoomStatus;


import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 描述游戏房间
 * @author: qigang
 * @create: 2018-07-05 12:05
 **/
public class Room {
    private Long roomId;     //房String间ID
    private int playerNum;    //玩家个数
    private ArrayList<Player> players;  //玩家列表
    private String mainPlayerId;   //房主ID
    private GameInfo gameInfo;

    public Room(Long roomId, int playerNum,Player mainPlayer) {
        this.roomId = roomId;
        this.playerNum = playerNum;
        this.players = Lists.newArrayListWithCapacity(playerNum);
        this.mainPlayerId = mainPlayer.getPlayerId();
        addPlayer(mainPlayer);
    }
    /** 
    * @Description: 房间的行为方法是对游戏局的出牌方法的封装  ,以下几个都是
    * @Param: [player, card] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/7/17 
    */
//    public void action(Player player,Card card,boolean isGetCard){
//        int flag = gameInfo.action(player,card,isGetCard);
//        if(flag == -1){
//            gameOver();
//        }
//    }

    public Player getCurrentPlayer() {
        return gameInfo.getCurrentPlayer();
    }

    public int action(Player player,Card card,Boolean isGetCard,String tempColor){
        //超级牌要记得变得颜色是什么
        if(card != null && (card.getFunc().equals(CardStatus.CRAD_FUNC_CHANGE) || card.getFunc().equals(CardStatus.CRAD_FUNC_TRUMP))){
            if(tempColor == null) {
                return RoomStatus.DISCARD_ACTION_ERROR;
            }
            gameInfo.setTempColor(tempColor);
        }
        int result =  gameInfo.action(player,card,isGetCard);
        if(result == -1){
            gameOver(player);
        }
        return result;

    }



    public ArrayList<Card> getPlayerRudge(String playerId){
        return gameInfo.getPlayerRudge(playerId);
    }

    public Card getPrevCard() {
        return gameInfo.getPrevCard();
    }

    public LinkedBlockingQueue<Card> getCardPile() {
        return gameInfo.getCardPile();
    }

    public ArrayList<Card> getDiscardPile() {
        return gameInfo.getDiscardPile();
    }

    //房间行为逻辑
    public void addPlayer(Player player){
        players.add(player);
    }

    public void playerExit(Player player){
        players.remove(player);
    }
    public boolean startGame(){
        if(playerNum != players.size()){
            return false;
        }
        gameInfo = new GameInfo(players);
        gameInfo.startGame(players.get(0));
        return true;
    }

    public String getRoomGameEvent(){
        return null;
    }



    //一局游戏结束，清理游戏信息
    public void gameOver(Player playerWin){

        //此处应该统计分数并记录到数据库中

        gameInfo = null;

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

//    public GameInfo getGameInfo() {
//        return gameInfo;
//    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public String getMainPlayerId() {
        return mainPlayerId;
    }

    public void setMainPlayerId(String mainPlayerId) {
        this.mainPlayerId = mainPlayerId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", playerNum=" + playerNum +
                ", players=" + players +
                ", mainPlayerId=" + mainPlayerId +
                '}';
    }
}
