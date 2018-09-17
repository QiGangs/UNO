package com.qi.uno.util;

import com.qi.uno.model.entiy.Card;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

/**
 * @description:
 * @author: qigang
 * @create: 2018-09-17 11:50
 **/
public class RoomUtil {
    public static Player findPlayerById(Room room, String playerid){
        ArrayList<Player> players = room.getPlayers();
        for(Player player : players){
            if(player.getPlayerId().equals(playerid)){
                return player;
            }
        }
        return null;
    }

    public static Card findCradById(Player player,String cardid){
        ArrayList<Card> cards = player.getRudge();
        for(Card card : cards){
            if(card.getId() == Integer.parseInt(cardid)){
                return card;
            }
        }
        return null;
    }
}
