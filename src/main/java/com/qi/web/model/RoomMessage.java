package com.qi.web.model;

import com.qi.uno.model.entiy.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * @description:
 * @author: qigang
 * @create: 2018-09-17 12:02
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomMessage {
    private ArrayList<Card> cards;

    private String CurrentPlayerId;

    private Card prevCard;

    private int CardPileSize;

    private int DiscardPileSize;


}
