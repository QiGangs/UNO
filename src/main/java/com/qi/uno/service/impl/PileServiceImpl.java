package com.qi.uno.service.impl;

import com.google.common.collect.Lists;
import com.qi.uno.common.CardHeapStatus;
import com.qi.uno.common.CardStatus;
import com.qi.uno.model.entiy.Card;
import com.qi.uno.service.PileService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description:
 * @author: qigang
 * @create: 2018-07-05 12:12
 **/
public class PileServiceImpl implements PileService {

    @Override
    public LinkedBlockingQueue<Card> getRuffleCard() {
        List<Card> newCardPile = Lists.newArrayList(CardHeapStatus.allCard);
        Collections.shuffle(newCardPile);
        newCardPile.get(0);
        return new LinkedBlockingQueue<>(newCardPile);
    }

    @Override
    public ArrayList<Card> getFirstSevenCard(LinkedBlockingQueue<Card> cardPile) {
        ArrayList<Card> rudge = Lists.newArrayList(CardHeapStatus.allCard);
        for(int i = 0;i<7;i++) {
            rudge.add(cardPile.poll());
        }
        return rudge;
    }

    @Override
    public ArrayList<Card> getSomeCard(LinkedBlockingQueue<Card> cardPile, int sum) {
        ArrayList<Card> rudge = Lists.newArrayListWithCapacity(sum);
        for(int i = 0;i<sum;i++) {
            rudge.add(cardPile.poll());
        }
        return rudge;
    }

    @Override
    public int findCardById(ArrayList<Card> rudge, int id) {
        for(int i = 0;i<rudge.size();i++){
            if(rudge.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }


//    @Override
//    public ArrayList<Card> getCanUsedCards(Card prevCard, ArrayList<Card> rudge) {
//        ArrayList<Card> arrayList = Lists.newArrayList();
//        for(Card card : rudge){
//            if(prevCard.getFunc().equals(CardStatus.CARD_TYPE_NUM)){
//                if(card.getColor().equals(prevCard.getColor()) || card.getNum() == prevCard.getNum() || !card.getFunc().equals(CardStatus.CARD_TYPE_NUM)){
//                    arrayList.add(card);
//                }
//            }else if(prevCard.getFunc().equals(CardStatus.CARD_TYPE_FUNC)){
////                if(){
////
////                }
//            }
//        }
//        return null;
//    }
}