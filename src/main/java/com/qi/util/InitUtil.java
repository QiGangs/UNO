package com.qi.util;

import com.google.common.collect.Lists;
import com.qi.uno.common.CardStatus;
import com.qi.uno.model.entiy.Card;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @description:
 * @author: qigang
 * @create: 2018-07-05 16:15
 **/
public class InitUtil {
    private int h = 1;
    public ArrayList<Card> getInitCardHeap() {
        ArrayList<Card> allCard = Lists.newArrayListWithCapacity(CardStatus.CARD_ALL_NUM);
        LinkedList<String> color = CardStatus.getAllColor();
        LinkedList<Integer> num = CardStatus.getAllNum();
        LinkedList<String> func = CardStatus.getFunc();
        LinkedList<String> highFunc = CardStatus.getHighFunc();
        //初始化0-9 的76张牌
        for(Integer i : num){
            for(String s: color){
                allCard.add(new Card(h++,CardStatus.CARD_TYPE_NUM,i,CardStatus.CRAD_NOT_FUNC,s));
            }
            if(i == 0){continue;}
            for(String s: color){
                allCard.add(new Card(h++,CardStatus.CARD_TYPE_NUM,i,CardStatus.CRAD_NOT_FUNC,s));
            }
        }
        //初始化 3*4*2 = 24 张普通功能牌
        for(String s : color){
            for(String x : func){
                allCard.add(new Card(h++,CardStatus.CARD_TYPE_FUNC,CardStatus.CRAD_NOT_NUM,x,s));
            }
            for(String x : func){
                allCard.add(new Card(h++,CardStatus.CARD_TYPE_FUNC,CardStatus.CRAD_NOT_NUM,x,s));
            }
        }
        //初始化 2*4 = 8张高级功能牌
        for(int i = 0;i<=3;i++){
            for(String s : highFunc){
                allCard.add(new Card(h++,CardStatus.CARD_TYPE_WILD,CardStatus.CRAD_NOT_NUM,s,CardStatus.CRAD_NOT_COLOR));
            }
        }
        return allCard;
    }
}
