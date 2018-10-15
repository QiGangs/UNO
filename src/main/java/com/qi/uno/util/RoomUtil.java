package com.qi.uno.util;

import com.qi.uno.model.entiy.Card;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.util.DateUtil;
import com.qi.web.websocket.WebSocketServer;

import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.Date;

/**
 * @description:
 * @author: qigang
 * @create: 2018-09-17 11:50
 **/
public class RoomUtil {

    //消息体还未定义
    public static void sendRoomInfoBackClient(Room room) throws IOException {
        for(WebSocketServer webSocket : room.getWebSocketSet()){
            webSocket.sendMessage(" ");
        }
    }








    public static Player findPlayerById(Room room, String playerid){
        ArrayList<Player> players = room.getPlayers();
        for(Player player : players){
            if(player.getPlayerId().equals(playerid)){
                return player;
            }
        }
        return null;
    }

    public static Card findCradById(Player player,String cardid){
        ArrayList<Card> cards = player.getRudge();
        for(Card card : cards){
            if(card.getId() == Integer.parseInt(cardid)){
                return card;
            }
        }
        return null;
    }

    public static String getRoomId(){
        long nowDate = DateUtil.currentDate().getTime();
        String sid = Integer.toHexString((int)nowDate);
        return sid;
    }

    public static void main(String[] args){
        System.out.println(getRoomId());
    }
}
