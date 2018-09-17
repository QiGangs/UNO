package com.qi.web.api;

import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.util.DateUtil;
import com.qi.util.json.JsonUtils;
import com.qi.web.common.GlobalObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description:
 * @author: qigang
 * @create: 2018-09-01 12:02
 **/
@RestController
@RequestMapping("/test")
public class TestApi {

    //返回一字符串当账号
    @RequestMapping(value = "/login")
    public String test2(){
        return ""+System.currentTimeMillis();
    }

    //创建房间
    @RequestMapping(value = "/room/create")
    public String test1(String playerid){
        Room room = new Room(new Long(1001),2,new Player(playerid));
        GlobalObject.AllRoom.put("1001",room);
        return "1001";
    }

    /**
    * @Description: 加入房间之后需要 开始建立websocket  传递房间信息以及 游戏信息
    * @Param: [playerid, roomid]
    * @return: java.lang.String
    * @Author: qigang
    * @Date: 2018/9/14
    */
    //加入房间
    @RequestMapping(value = "/room/join")
    public String test3(String playerid,String roomid){
        Room room = (Room) GlobalObject.AllRoom.get(roomid);
        if(!room.getStarted()){
            return "error";
        }
        room.addPlayer(new Player(playerid));
        return "1001";
    }

    //以下接口当由websocket传递数据，不在使用http的形式




    //应当合并于 获取用户信息的接口中   （弃用）
    //获取房间状态信息，即是否开启游戏
    @RequestMapping(value = "/room/status/get")
    public String test6(String playerid,String roomid){
        Room room = (Room) GlobalObject.AllRoom.get(roomid);
        room.addPlayer(new Player(playerid));
        return "1001";
    }

    //获取游戏的具体信息，谁要出牌，上一张牌是什么，牌堆牌，弃牌堆牌，我这个用户的手牌是什么
    @RequestMapping(value = "/room/game/info/get")
    public String test7(String playerid,String roomid){
//        Room room = (Room) GlobalObject.AllRoom.get(roomid);
//        room.addPlayer(new Player(playerid));
        return "";
    }


    //启动游戏 进入游戏界面
    @RequestMapping(value = "/room/game/start")
    public String test5(String roomid){
        Room room = (Room) GlobalObject.AllRoom.get(roomid);
        boolean res = room.startGame();

        System.out.println("当前出牌玩家："+room.getCurrentPlayer().getPlayerId());
        System.out.println("上一张牌："+room.getPrevCard());
        System.out.println("摸牌堆："+room.getCardPile().size());
        System.out.println("弃牌堆："+room.getDiscardPile().size());
        return res+"";
    }

    //如果游戏启动了，要返回正在游戏中的转态，让客户端切换界面
    //获取房间内用户成员   希望可以切换到流式接口  用 webflux  （需要测试）
    @RequestMapping(value = "/room/player/get",produces="application/json;charset=UTF-8")
    public String test4(String roomid){
//        System.out.println("i am sd");
        Room room = (Room) GlobalObject.AllRoom.get(roomid);
        return JsonUtils.writeObjectToJson(room.getPlayers());
    }

    //登录时要判断用户状态，是不是在某个房间内
    @RequestMapping(value = "/person/status/get")
    public String test(){
        return JsonUtils.writeObjectToJson("heiehiehi");
    }


}
