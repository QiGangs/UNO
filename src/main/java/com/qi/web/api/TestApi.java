package com.qi.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description:
 * @author: qigang
 * @create: 2018-09-01 12:02
 **/
@RestController
public class TestApi {

    @RequestMapping("/t")
    public Object test(){
        return "OJBK";
    }


}
