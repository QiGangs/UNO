package com.qi.web.service.impl;

import com.qi.uno.common.CardHeapStatus;
import com.qi.uno.model.entiy.Card;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.util.json.JsonUtils;
import com.qi.web.common.MessageStatus;
import com.qi.web.model.DataGameInfo;
import com.qi.web.model.DataRoom;
import com.qi.web.model.Message;
import com.qi.web.service.SocketService;
import com.qi.web.websocket.WebSocketServer;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: qigang
 * @create: 2018-10-15 21:17
 **/
@Service
public class SocketServiceImpl implements SocketService {
    @Override
    public String getRoomId(){
        long nowDate = (new Date()).getTime();
        return Integer.toHexString((int)nowDate)+(int)(Math.random()*100);
    }

    private SocketServiceImpl(){

    }

    public static SocketService getSocketService(){
        return new SocketServiceImpl();
    }


    @Override
    public void sendRoomInfoBackClient(Room room) throws IOException, InterruptedException {

        Thread.sleep(1000);

        for(WebSocketServer webSocket : room.getWebSocketSet()){
            Message message = Message.getMessage(MessageStatus.MESSAGE_TYPE_ROOM,
                    DataRoom.getDataRoom(room.getRoomId(),room.getPlayerNum(), room.getPlayers(),room.getStarted(),room.getMainPlayerId()));
            webSocket.sendMessage(JsonUtils.writeObjectToJson(message));
        }
    }

    @Override
    public void sendRoomMissToAll(Room room) throws IOException, InterruptedException {
        for(WebSocketServer webSocket : room.getWebSocketSet()){
            Message message = Message.getMessage(MessageStatus.MESSAGE_TYPE_EXIT_GAME,
                    null);
            webSocket.sendMessage(JsonUtils.writeObjectToJson(message));
            webSocket.onClose();
        }

    }

    @Override
    public Boolean isCanStartGame(Room room) {
        if(room.getPlayers().size() < room.getPlayerNum()){
            return false;
        }
        return true;
    }

    @Override
    public Boolean startGame(Room room) {
        return room.startGame();
    }

    /** 
    * @Description: 发送游戏启动的信息，启动游戏，发送游戏牌局信息 
    * @Param: [room] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/10/15 
    */
    @Override
    public void sendStartGameToAll(Room room) throws IOException, InterruptedException {
        for(WebSocketServer webSocket : room.getWebSocketSet()){
            Message message = Message.getMessage(MessageStatus.MESSAGE_TYPE_START_GAME,
                    null);
            webSocket.sendMessage(JsonUtils.writeObjectToJson(message));
        }
        //延时1秒后启动服务端游戏逻辑
        Thread.sleep(1000);
        startGame(room);

        sendRoomInfoToAll(room);
    }

    @Override
    public void sendRoomInfoToAll(Room room) throws IOException, InterruptedException {
        for(WebSocketServer webSocket : room.getWebSocketSet()){
            Message message = Message.getMessage(MessageStatus.MESSAGE_TYPE_GAME_INFO,
                    DataGameInfo.getInstance(room.getRoomId(),room.getCardPile().size(),room.getDiscardPile().size(),room.getPrevCard(),webSocket.getPlayer(),room.getCurrentPlayer().getPlayerId()));
            webSocket.sendMessage(JsonUtils.writeObjectToJson(message));
        }
    }

    @Override
    public void dealPutCardAction(Room room, Object info) throws IOException, InterruptedException {
        int cardid = (Integer) ((Map)info).get("putcardid");
        String playerid = (String)((Map)info).get("putplayerid");
        String tempColor = null;
        String _tempColor = (String)((Map)info).get("tempcolor");

        if(!(_tempColor == null || "".equals(_tempColor))){
            tempColor = _tempColor;
        }

        //System.out.println(tempColor+_tempColor);

        Player player = null;
        Card card = null;

        for (Player p : room.getPlayers()) {
            if (p.getPlayerId().equals(playerid)) {
                player = p;
            }
        }

        if(cardid != -1) {

            for (Card c : CardHeapStatus.allCard) {
                if (c.getId() == cardid) {
                    card = c;
                } else {
//                System.out.println(" before return  put card error in SocketServiceImpl");
//                return;
                }
            }

            int res = room.action(player, card, false, tempColor);
            if (res == 1) {
                sendRoomInfoToAll(room);
            } else {
                System.out.println("put card error in SocketServiceImpl");
            }
        }else if(cardid == -1){
            int res = room.action(player, null,true,null);
            if (res == 1) {
                sendRoomInfoToAll(room);
            } else {
                System.out.println("get card error in SocketServiceImpl");
            }
        }
    }
}
