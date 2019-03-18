package com.qi.web.service.impl;

import com.google.gson.Gson;
import com.qi.uno.model.entiy.Room;
import com.qi.util.json.JsonUtils;
import com.qi.web.common.GlobalObject;
import com.qi.web.model.entity.RoomListDTO;
import com.qi.web.service.ManageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: qigang
 * @create: 2019-03-18 19:45
 **/
@Service
public class ManageServiceImpl implements ManageService {

    @Override
    public String getRoomList() {
        List<RoomListDTO> list = new LinkedList<>();
        for(String rkey: GlobalObject.AllRoom.keySet()){
            Room room  = (Room) GlobalObject.AllRoom.get(rkey);
            list.add(new RoomListDTO(rkey,room.getPlayers().size(),room.getStarted()));
        }
       return  JsonUtils.writeObjectToJson(list);
    }
}
