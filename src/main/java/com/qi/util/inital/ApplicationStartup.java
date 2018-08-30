package com.qi.util.inital;

import com.qi.uno.common.CardHeapStatus;
import com.qi.util.InitUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @description:
 * @author: qigang
 * @create: 2018-07-05 13:41
 **/
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    /** 
    * @Description: 初始化时，用于构建一副未进行洗牌的牌堆 
    * @Param: [contextRefreshedEvent] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/7/5 
    */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        InitUtil initUtil = new InitUtil();
        CardHeapStatus.allCard = initUtil.getInitCardHeap();
    }
}
