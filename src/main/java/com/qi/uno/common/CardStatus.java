package com.qi.uno.common;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * @description: 描述每张牌的常量 万能牌算没有颜色的功能牌
 * @author: qigang
 * @create: 2018-07-05 11:05
 **/
public class CardStatus {
    public static int CARD_ALL_NUM = 108;

    public static String CARD_TYPE_NUM = "num";
    public static String CARD_TYPE_FUNC = "func";
    public static String CARD_TYPE_WILD = "wild";


    public static String CRAD_COLOR_R = "red";
    public static String CRAD_COLOR_Y = "yellow";
    public static String CRAD_COLOR_B = "blue";
    public static String CRAD_COLOR_G = "green";

    public static String CRAD_NOT_COLOR = "nocolor";


    public static String CRAD_FUNC_STOP = "stop";
    public static String CRAD_FUNC_REVERSE = "reverse";
    public static String CRAD_FUNC_ADD2 = "add2";

    public static String CRAD_FUNC_CHANGE = "change";
    public static String CRAD_FUNC_TRUMP = "trump";

    public static String CRAD_NOT_FUNC = "nofunc";




    public static int CRAD_NUM_1 = 1;
    public static int CRAD_NUM_2 = 2;
    public static int CRAD_NUM_3 = 3;
    public static int CRAD_NUM_4 = 4;
    public static int CRAD_NUM_5 = 5;
    public static int CRAD_NUM_6 = 6;
    public static int CRAD_NUM_7 = 7;
    public static int CRAD_NUM_8 = 8;
    public static int CRAD_NUM_9 = 9;
    public static int CRAD_NUM_0 = 0;

    public static int CRAD_NOT_NUM = -1;


    public static LinkedList<String> getFunc(){
        LinkedList<String> func = Lists.newLinkedList();
        func.add(CardStatus.CRAD_FUNC_STOP);
        func.add(CardStatus.CRAD_FUNC_REVERSE);
        func.add(CardStatus.CRAD_FUNC_ADD2);
        return func;
    }

    public static LinkedList<String> getHighFunc(){
        LinkedList<String> highFunc = Lists.newLinkedList();
        highFunc.add(CardStatus.CRAD_FUNC_CHANGE);
        highFunc.add(CardStatus.CRAD_FUNC_TRUMP);
        return highFunc;
    }

    public static LinkedList<String> getAllColor(){
        LinkedList<String> color = Lists.newLinkedList();
        color.add(CardStatus.CRAD_COLOR_B);
        color.add(CardStatus.CRAD_COLOR_Y);
        color.add(CardStatus.CRAD_COLOR_G);
        color.add(CardStatus.CRAD_COLOR_R);
        return color;
    }

    public static LinkedList<Integer> getAllNum(){
        LinkedList<Integer> num = Lists.newLinkedList();
        num.add(CardStatus.CRAD_NUM_0);
        num.add(CardStatus.CRAD_NUM_1);
        num.add(CardStatus.CRAD_NUM_2);
        num.add(CardStatus.CRAD_NUM_3);
        num.add(CardStatus.CRAD_NUM_4);
        num.add(CardStatus.CRAD_NUM_5);
        num.add(CardStatus.CRAD_NUM_6);
        num.add(CardStatus.CRAD_NUM_7);
        num.add(CardStatus.CRAD_NUM_8);
        num.add(CardStatus.CRAD_NUM_9);
        return num;
    }



}
