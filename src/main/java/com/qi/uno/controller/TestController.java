package com.qi.uno.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.qi.uno.common.CardHeapStatus;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: qigang
 * @create: 2018-07-05 12:14
 **/
@RestController
//@RequestMapping("/test")
public class TestController {


    @GetMapping("/get")
    public String getAllCard() {

        Gson gson = new Gson();
        return gson.toJson(CardHeapStatus.allCard)+"\n"+CardHeapStatus.allCard.size();
    }

    @GetMapping("/test")
    public String getSuffleCard() {
        Gson gson = new Gson();
        return gson.toJson(new Room(new Long(123),123,new Player(new Long(123))).getCardPile());
    }


}
