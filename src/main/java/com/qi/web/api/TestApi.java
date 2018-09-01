package com.qi.web.api;

import com.qi.util.json.JsonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: qigang
 * @create: 2018-09-01 12:02
 **/
@RestController
public class TestApi {

    @RequestMapping(value = "/person/status/get")
    public String test(){

        return JsonUtils.writeObjectToJson("heiehiehi");
    }
}
