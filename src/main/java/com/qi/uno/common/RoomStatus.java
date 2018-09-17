package com.qi.uno.common;

import com.qi.uno.model.entiy.Card;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.uno.util.RoomUtil;
import com.qi.util.json.JsonUtils;
import com.qi.web.common.GlobalObject;
import com.qi.web.model.MessageJson;
import com.qi.web.model.RoomMessage;
import com.qi.web.websocket.WebSocketServer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @description:
 * @author: qigang
 * @create: 2018-07-06 13:05
 **/
public class RoomStatus {
    public static int DISCARD_DIRECTION_NEXT = 1;
    public static int DISCARD_DIRECTION_PREV = -1;


    public static int DISCARD_ACTION_ERROR = 1001;

    //每当有人进入房间  调用此方法   回传用户信息
    public static synchronized void roomPlayer(String roomid,String playerid) throws IOException {
        Room room = (Room) GlobalObject.AllRoom.get(roomid);

        MessageJson messageJson = new MessageJson(roomid,room.getPlayers());

        for(WebSocketServer webSocketServer : room.getWebSocketSet()){
            webSocketServer.sendMessage(JsonUtils.writeObjectToJson(messageJson));
        }

    }


    //修改room状态的方法   message 必须为json
    public static synchronized void changeRoom(String roomid,String playerid,String message) throws IOException{
        Room room = (Room) GlobalObject.AllRoom.get(roomid);

        //执行操作
        MessageJson messageJson = JsonUtils.readJsonToObject(MessageJson.class,message);
        Player player = RoomUtil.findPlayerById(room,messageJson.getPlayerid());
        Card card = RoomUtil.findCradById(player,messageJson.getCardid());

        int res = room.action(player ,card, card == null? true:false,null);


        //回传状态
        for(WebSocketServer webSocketServer : room.getWebSocketSet()){
            String id = webSocketServer.getPlayerid();
            ArrayList<Card> cards = RoomUtil.findPlayerById(room,messageJson.getPlayerid()).getRudge();
            String currentPlayerid = room.getCurrentPlayer().getPlayerId();
            Card prevCard = room.getPrevCard();
            int CardPileSize = room.getCardPile().size();
            int DiscardPileSize = room.getDiscardPile().size();
            webSocketServer.sendMessage(JsonUtils.writeObjectToJson(new RoomMessage(cards,currentPlayerid,prevCard,CardPileSize,DiscardPileSize)));
        }
        //获得房间对象

        //第一步判断是否符合要求

        //第二步  执行

        //第步    回传转态
    }
}
