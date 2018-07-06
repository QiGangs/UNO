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
        return new LinkedBlockingQueue<>(newCardPile);
    }
}