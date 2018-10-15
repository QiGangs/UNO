package com.qi.web.model;

import com.qi.uno.model.entiy.Player;

import java.util.List;

/** ex
 * @description:
 * @author: qigang
 * @create: 2018-10-15 14:02
 **/
public class DataRoom implements Data{
    private String roomId;
    private Integer roomMaxNum;
    private List<Player> players;
    private Boolean isStarted;
    private String mainPlayerId;

    private DataRoom(String roomId,Integer roomMaxNum, List<Player> players, Boolean isStarted, String mainPlayerId) {
        this.roomId = roomId;
        this.roomMaxNum = roomMaxNum;
        this.players = players;
        this.isStarted = isStarted;
        this.mainPlayerId = mainPlayerId;
    }


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public static DataRoom getDataRoom(String roomId,Integer roomMaxNum, List<Player> players, Boolean isStarted, String mainPlayerId){
        return new DataRoom(roomId,roomMaxNum,players,isStarted,mainPlayerId);
    }

    public Integer getRoomMaxNum() {
        return roomMaxNum;
    }

    public void setRoomMaxNum(Integer roomMaxNum) {
        this.roomMaxNum = roomMaxNum;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Boolean getStarted() {
        return isStarted;
    }

    public void setStarted(Boolean started) {
        isStarted = started;
    }

    public String getMainPlayerId() {
        return mainPlayerId;
    }

    public void setMainPlayerId(String mainPlayerId) {
        this.mainPlayerId = mainPlayerId;
    }

    @Override
    public String toString() {
        return "DataRoom{" +
                "roomMaxNum=" + roomMaxNum +
                ", players=" + players +
                ", isStarted=" + isStarted +
                ", mainPlayerId='" + mainPlayerId + '\'' +
                '}';
    }
}
