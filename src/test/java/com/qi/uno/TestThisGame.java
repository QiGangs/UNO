package com.qi.uno;

import com.qi.uno.common.CardHeapStatus;
import com.qi.uno.model.entiy.Card;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.uno.utils.InitUtil;

import java.util.Scanner;

/**
 * @description:
 * @author: qigang
 * @create: 2018-07-09 16:30
 **/
public class TestThisGame {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        InitUtil initUtil = new InitUtil();
        CardHeapStatus.allCard = initUtil.getInitCardHeap();
        Room room = new Room(new Long(123),2,new Player(new Long(111)));
        room.addPlayer(new Player(new Long(222)));
        room.startGame();

        show(room);



        int i = scanner.nextInt();

        Card card = room.getGameInfo().getPlayerRudge(new Long(111)).get(i);

        room.getGameInfo().putCard(room.getGameInfo().getCurrentPlayer(),card);

        show(room);

         i = scanner.nextInt();

         card = room.getGameInfo().getPlayerRudge(new Long(222)).get(i);

        room.getGameInfo().putCard(room.getGameInfo().getCurrentPlayer(),card);

        show(room);
    }


    public static void show(Room room){
        System.out.println(room.getGameInfo().getPlayerRudge(new Long(222)));
        System.out.println(room.getGameInfo().getPlayerRudge(new Long(111)));
        System.out.println(room.getGameInfo().getCurrentPlayer());
        System.out.println(room.getGameInfo().getPrevCard());
        System.out.println(room.getGameInfo().getCardPile().size());
        System.out.println(room.getGameInfo().getDiscardPile().size());
    }
}
