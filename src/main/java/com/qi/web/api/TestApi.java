package com.qi.web.api;

import com.google.common.collect.Maps;
import com.qi.util.json.JsonUtils;
import com.qi.web.common.GlobalObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: qigang
 * @create: 2018-09-01 12:02
 **/
@RestController
@RequestMapping("/test")
public class TestApi {

    @RequestMapping("/t")
    public Object test(){
        return "OK";
    }

//    @RequestMapping("/g")
//    public Object test1(){
//        Map<String,Object> data = Maps.newHashMap();
//        data.put("type","allinfo");
//        data.put("info",GlobalObject.AllRoom.toString());
//        return JsonUtils.writeObjectToJson(data);
//    }


}
