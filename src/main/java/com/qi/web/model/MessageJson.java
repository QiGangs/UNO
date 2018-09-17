package com.qi.web.model;

import com.qi.uno.model.entiy.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * @description: 描述 websocket传输过程的json格式
 * @author: qigang
 * @create: 2018-09-16 23:18
 **/
@Getter
@Setter
@ToString
public class MessageJson {
    private String roomid;
    private ArrayList<Player> players;

    private String playerid;
    private String cardid;


    public MessageJson(String roomid, ArrayList<Player> players) {
        this.roomid = roomid;
        this.players = players;
    }

    public MessageJson(String roomid, String playerid, String cardid) {
        this.roomid = roomid;
        this.playerid = playerid;
        this.cardid = cardid;
    }
}
