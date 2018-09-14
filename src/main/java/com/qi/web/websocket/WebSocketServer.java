package com.qi.web.websocket;

import com.qi.uno.model.entiy.Room;
import com.qi.web.common.GlobalObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description:   serverEndpoint 需要提供 用户角色,房间号，
 *                  此服务只用来建立socket   webSocket由房间进行管理
 * @author: qigang
 * @create: 2018-09-12 14:28
 **/
@ServerEndpoint("/websocket/{roomid}/{playerid}")
@Component
public class WebSocketServer {

//    static Log log=LogFactory.get(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据

    //接收sid
//    private String sid="";

    private Session session;
    private String playerid="";

    /** 
    * @Description: 接受来自客户端的连接，根据参数将对应的连接放入对应的房间
    * @Param: [session, roomid, playerid] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/9/14 
    */
    @OnOpen
    public void onOpen(Session session,@PathParam("roomid") String roomid,@PathParam("playerid") String playerid) {
            //以后需要修改为传递授权码 auth  更加auth获得playerid，返回赋值给sid
        this.session = session;
        Room room = (Room) GlobalObject.AllRoom.get(roomid);
        room.getWebSocketSet().add(this);
        this.playerid=playerid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("roomid") String roomid) {
        Room room = (Room) GlobalObject.AllRoom.get(roomid);
        room.getWebSocketSet().remove(this);  //从set中删除
//        subOnlineCount();           //在线数减1
//        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {

        //to-do
        //做接收到数据后的操作


//        log.info("收到来自窗口"+sid+"的信息:"+message);
        //群发消息
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
//        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
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


//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    public static synchronized void addOnlineCount() {
//        WebSocketServer.onlineCount++;
//    }
//
//    public static synchronized void subOnlineCount() {
//        WebSocketServer.onlineCount--;
//    }
}
