package com.qi.web.api;

import com.qi.util.json.JsonUtils;
import com.qi.web.websocket.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @description:
 * @author: qigang
 * @create: 2018-09-12 14:42
 **/
@Controller
@RequestMapping("/test")
public class Test2Api {


    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid, String message) {
//        try {
//            WebSocketServer.sendInfo(message,cid);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return JsonUtils.writeObjectToJson(cid+"#"+e.getMessage());
//        }
        return JsonUtils.writeObjectToJson(cid);
    }
}
