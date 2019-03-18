package com.qi.web.api;

import com.google.common.collect.Maps;
import com.qi.util.json.JsonUtils;
import com.qi.web.common.GlobalObject;
import com.qi.web.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private ManageService manageService;

    @RequestMapping("/t")
    public Object test(){
        return "OK";
    }

    @RequestMapping("/q")
    public Object testx(){
        return manageService.getRoomList();
    }


}
