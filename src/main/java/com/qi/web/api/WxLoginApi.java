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

    @RequestMapping(value = "/login",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String onLogin(@RequestParam String code){
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
