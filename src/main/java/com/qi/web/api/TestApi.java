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

    @RequestMapping(value = "/room/create")
    public String test1(String playerid){
        Room room = new Room(new Long(1001),2,new Player(playerid));
        GlobalObject.AllRoom.put("1001",room);
        return "1001";
    }

    @RequestMapping(value = "/room/join")
    public String test3(String playerid,String roomid){
        Room room = (Room) GlobalObject.AllRoom.get(roomid);
        room.addPlayer(new Player(playerid));
        return "1001";
    }

    @RequestMapping(value = "/room/player/get",produces="application/json;charset=UTF-8")
    public String test4(String roomid){
        System.out.println("i am sd");
        Room room = (Room) GlobalObject.AllRoom.get(roomid);
        return JsonUtils.writeObjectToJson(room.getPlayers());
    }

    @RequestMapping(value = "/person/status/get")
    public String test(){
        return JsonUtils.writeObjectToJson("heiehiehi");
    }


}
