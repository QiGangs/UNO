package com.qi.web.websocket;

import com.qi.uno.common.RoomStatus;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.uno.util.RoomUtil;
import com.qi.util.json.JsonUtils;
import com.qi.web.common.GlobalObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description:   serverEndpoint 需要提供 用户角色,房间号，
 *                  此服务只用来建立socket   webSocket由房间进行管理
 *                  事件驱动
 * @author: qigang
 * @create: 2018-09-12 14:28
 **/
@ServerEndpoint("/websocket/{roomid}/{playerid}")
@Component
public class WebSocketServer {

    //记录room来判定套接字从属房间
    private Room room;
    private Session session;
    private String playerid="";

    /** 
    * @Description: 接受来自客户端的连接，根据参数将对应的连接放入对应的房间
    * @Param: [session, roomid, playerid]  roomid = 000000 执行创建操作
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/9/14 
    */
    @OnOpen
    public void onOpen(Session session,@PathParam("roomid") String roomid,@PathParam("playerid") String playerid) throws IOException, InterruptedException {
            //以后需要修改为传递授权码 auth  更加auth获得playerid，返回赋值给sid
        this.session = session;

        Room room = null;
        if(roomid.equals(RoomStatus.DEFAULT_ROOM_ID)){
            //新建房间的指令
            room = Room.getRoom(RoomUtil.getRoomId(),RoomStatus.DEFAULT_ROOM_PLAYER_NUM,Player.getPlayer(playerid));
            GlobalObject.AllRoom.put(room.getRoomId(),room);
        }else {
            room  = (Room) GlobalObject.AllRoom.get(roomid);
            if(room == null){
                //房间不存在就直接创建房间
                room = Room.getRoom(RoomUtil.getRoomId(),RoomStatus.DEFAULT_ROOM_PLAYER_NUM,Player.getPlayer(playerid));
                GlobalObject.AllRoom.put(room.getRoomId(),room);
            }else {
                //不然此用户加入房间
                room.addPlayer(Player.getPlayer(playerid));
            }

        }
        room.getWebSocketSet().add(this);

        this.room = room;
        this.playerid=playerid;

        RoomUtil.sendRoomInfoBackClient(room);




    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        room.getWebSocketSet().remove(this);  //从set中删除
        System.out.println(room.getWebSocketSet().size());
        System.out.println("over!!!!");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message) throws IOException {
        Map<String,Object> map = JsonUtils.readJsonToMap(message);
        int flag = (Integer) map.get("type");
        if(flag == -1){
            onClose();
        }

        //sendMessage("hello,sb");
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        System.out.println("send to :" +getPlayerid());
        this.session.getBasicRemote().sendText(message);
    }

    public String getPlayerid() {
        return playerid;
    }

    /**
     * 群发自定义消息
//     * */
//    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
////        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                //这里可以设定只推送给这个sid的，为null则全部推送
//                if(sid==null) {
//                    item.sendMessage(message);
//                }else if(item.sid.equals(sid)){
//                    item.sendMessage(message);
//                }
//            } catch (IOException e) {
//                continue;
//            }
//        }
//    }

}
