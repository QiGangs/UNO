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
    private Long roomId;     //房间ID
    private int playerNum;    //玩家个数
    private ArrayList<Player> players;  //玩家列表
    private Long mainPlayerId;   //房主ID
    private GameInfo gameInfo;




    public Room(Long roomId, int playerNum,Player mainPlayer) {
        this.roomId = roomId;
        this.playerNum = playerNum;
        this.players = Lists.newArrayListWithCapacity(playerNum);
        this.mainPlayerId = mainPlayer.getPlayerId();
        addPlayer(mainPlayer);
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

    public void gameOver(){
        gameInfo = null;
    }

    public String getRoomGameEvent(){

        return null;
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

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
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
                ", mainPlayerId=" + mainPlayerId +
                '}';
    }
}
