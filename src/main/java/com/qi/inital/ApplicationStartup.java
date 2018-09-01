package com.qi.inital;

import com.google.common.collect.Maps;
import com.qi.uno.common.CardHeapStatus;
import com.qi.web.common.GlobalObject;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @description:
 * @author: qigang
 * @create: 2018-07-05 13:41
 **/
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    /** 
    * @Description: 初始化时，进行初始化操作
    * @Param: [contextRefreshedEvent] 
    * @return: void 
    * @Author: qigang 
    * @Date: 2018/7/5 
    */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //初始化牌堆
        InitUtil initUtil = new InitUtil();
        CardHeapStatus.allCard = initUtil.getInitCardHeap();
        //初始化游戏大厅空间  即存放游戏房间的空间
        GlobalObject.AllRoom = Maps.newConcurrentMap();
    }
}
