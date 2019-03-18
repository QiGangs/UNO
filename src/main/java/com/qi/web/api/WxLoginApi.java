package com.qi.web.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qi.web.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: qigang
 * @create: 2018-10-14 21:14
 **/
@RestController
@RequestMapping("/wx")
public class WxLoginApi {
    @Autowired
    private VerifyCodeService verifyCodeService;

    /** 
    * @Description: 主要用于微信授权认证，获得用户的OPENID来作为用户的唯一标识
    * @Param: [code] 
    * @return: java.lang.String 
    * @Author: qigang 
    * @Date: 2018/12/4 
    */
    @RequestMapping(value = "/login")
    @ResponseBody
    public String onLogin(@RequestParam String code){
        System.out.println("there are someone login");

        String res =  verifyCodeService.CheckMessageCode(code);
        if(res == null) {
            return "error";
        }
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(res);
        String openid = jsonObject.get("openid").getAsString();

        return openid;

//        Long customid = loginService.getStudentidByOpenid(openid);   //openid 换学号
//        if(customid == null){
//            return "3230178";
//        }else {
//            Student student = new Student();
//            student.setCustomdId(customid);
//            return studentService.getStudentEncode(student,true);  //学号换auth
//        }
    }
}
