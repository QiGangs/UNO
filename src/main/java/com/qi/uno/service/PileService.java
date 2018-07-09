package com.qi.uno.service;

import com.qi.uno.model.entiy.Card;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 描述牌堆服务的接口 如洗牌  ，生成108张牌等
 * @author: qigang
 * @create: 2018-07-05 12:11
 **/
public interface PileService {
    LinkedBlockingQueue<Card> getRuffleCard();


    ArrayList<Card> getFirstSevenCard(LinkedBlockingQueue<Card> cardPile);

    ArrayList<Card> getSomeCard(LinkedBlockingQueue<Card> cardPile,int sum);

    int findCardById(ArrayList<Card> rudge,int id);

//    ArrayList<Card> getCanUsedCards(Card prevCard,ArrayList<Card> rudge);
}
