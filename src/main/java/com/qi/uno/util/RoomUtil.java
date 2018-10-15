package com.qi.uno.util;

import com.qi.uno.model.entiy.Card;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.util.DateUtil;
import com.qi.util.json.JsonUtils;
import com.qi.web.common.MessageStatus;
import com.qi.web.model.DataRoom;
import com.qi.web.model.Message;
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
    public static synchronized void sendRoomInfoBackClient(Room room) throws IOException, InterruptedException {

        Thread.sleep(1000);

        for(WebSocketServer webSocket : room.getWebSocketSet()){
            Message message = Message.getMessage(MessageStatus.MESSAGE_TYPE_ROOM,
                    DataRoom.getDataRoom(room.getRoomId(),room.getPlayerNum(), room.getPlayers(),room.getStarted(),room.getMainPlayerId()));
            webSocket.sendMessage(JsonUtils.writeObjectToJson(message));

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

    public static synchronized String getRoomId(){
        long nowDate = (new Date()).getTime();
        return Integer.toHexString((int)nowDate)+(int)(Math.random()*100);
    }

    public static void main(String[] args){
        for(int i = 0;i<10;i++){
            System.out.println(getRoomId());
        }

    }
}
