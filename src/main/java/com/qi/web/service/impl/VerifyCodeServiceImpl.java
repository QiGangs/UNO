package com.qi.web.service.impl;

import com.qi.util.http.HttpResult;
import com.qi.util.http.HttpUtils;
import com.qi.web.service.VerifyCodeService;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: qigang
 * @create: 2018-10-14 21:11
 **/
@Component
public class VerifyCodeServiceImpl implements VerifyCodeService {
    private String address = "https://api.weixin.qq.com/sns/jscode2session?appid=wxce776a6c0e41c10b&secret=eeb4d264e256b7ba9094dacc953343a3&js_code=";
    private String add2 = "&grant_type=authorization_cody";

    @Override
    public String CheckMessageCode(String code) {
        //String s=httpsRequest(,"GET",null);
        HttpResult httpResult = HttpUtils.getUrlAsString(address + code.trim() + add2);
        return httpResult.getResponse();
    }
}
