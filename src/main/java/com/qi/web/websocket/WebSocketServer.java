package com.qi.web.websocket;

import com.qi.uno.common.RoomStatus;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.uno.util.RoomUtil;
import com.qi.util.json.JsonUtils;
import com.qi.web.common.GlobalObject;
import com.qi.web.service.SocketService;
import com.qi.web.service.impl.SocketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SocketService socketService = SocketServiceImpl.getSocketService();
    //记录room来判定套接字从属房间
    private Room room;
    private Session session;
    private String playerid="";
    private Player player;

    private int flag = 1;

    private int games = 1;

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
        player = Player.getPlayer(playerid);
        Room room = null;
        System.out.println("start game");
        if(roomid.equals(RoomStatus.DEFAULT_ROOM_ID)){
            //新建房间的指令
            room = Room.getRoom(socketService.getRoomId(),RoomStatus.DEFAULT_ROOM_PLAYER_ALL_NUM,player);
            GlobalObject.AllRoom.put(room.getRoomId(),room);
        }else {
            room  = (Room) GlobalObject.AllRoom.get(roomid);
            if(room == null){
                //房间不存在就直接创建房间
                room = Room.getRoom(socketService.getRoomId(),RoomStatus.DEFAULT_ROOM_PLAYER_ALL_NUM,player);
                GlobalObject.AllRoom.put(room.getRoomId(),room);
            }else {
                //不然此用户加入房间
                if(room.getPlayers().size()<room.getPlayerNum()){
                    room.addPlayer(player);
                }else {
                    //直接关闭套接字
                    flag = 0;
                    onClose();
                    return;
                }

            }

        }
        room.getWebSocketSet().add(this);

        this.room = room;
        this.playerid=playerid;

        socketService.sendRoomInfoBackClient(room);

        System.out.println("open");

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() throws IOException, InterruptedException {
        if(flag == 0){
            return;
        }
        //从set中删除
        int mx = 0;
        //移除前判断一下游戏进程,如果要移除的玩家是当前出牌玩家就直接跳往下一个玩家
        if(room.getCurrentPlayer().getPlayerId().equals(playerid)){
            System.out.println(room.getCurrentPlayer().getPlayerId());
            room.toNextPlayer();
            System.out.println("tonextplayer"+"\n"+room.getCurrentPlayer().getPlayerId());
            mx = 1;
        }
        room.getWebSocketSet().remove(this);
        room.getPlayers().remove(player);
        if(room.getWebSocketSet().size() == 0){
            GlobalObject.AllRoom.remove(room.getRoomId());
        }
        if(mx == 1){
            socketService.sendRoomInfoToAll(room);
        }
        System.out.println("over!!!!");

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message) throws IOException, InterruptedException {
        Map<String,Object> map = JsonUtils.readJsonToMap(message);
        int flag = (Integer) map.get("type");
//        System.out.println(map.get("data"));
        if(flag == -1 && !room.getMainPlayerId().equals(map.get("data"))){
            onClose();
            //我也不记得这个代码干啥了
            //socketService.sendRoomInfoToAll(room);
        }else if(flag == -1 && room.getMainPlayerId().equals(map.get("data"))){
            //移除所有客户端
            socketService.sendRoomMissToAll(room);
        }else if(flag == 110 && !room.getStarted()){
            if(socketService.isCanStartGame(room)){
                socketService.sendStartGameToAll(room);
            }
        }else if(flag == 3){
            Object tmp = map.get("data");
            socketService.dealPutCardAction(room,tmp);
//            System.out.println(tmp.toString());
        }else {

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
        //System.out.println("send to :" +getPlayerid());
        this.session.getBasicRemote().sendText(message);
    }

    public String getPlayerid() {
        return playerid;
    }

    public Player getPlayer() {
        return player;
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
