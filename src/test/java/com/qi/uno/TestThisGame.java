package com.qi.uno;

import com.qi.uno.common.CardHeapStatus;
import com.qi.uno.common.CardStatus;
import com.qi.uno.model.entiy.Card;
import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.util.InitUtil;

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
        Room room = new Room(new Long(123),3,new Player(new Long(111)));
        room.addPlayer(new Player(new Long(222)));
        room.addPlayer(new Player(new Long(333)));
        room.startGame();

        show(room);

        int i;
        Card card;


        while (true) {
            System.out.println("please choose player and card num");
            int x = scanner.nextInt();
            i = scanner.nextInt();
            if(i == -1){
                room.action(room.getCurrentPlayer(), null,true);
            }else {
                switch (x){
                    case 1:card = room.getPlayerRudge(new Long(111)).get(i);break;
                    case 2:card = room.getPlayerRudge(new Long(222)).get(i);break;
                    case 3:card = room.getPlayerRudge(new Long(333)).get(i);break;
                    default:card = null;
                }

                if(card.getType().equals(CardStatus.CARD_TYPE_WILD)){
                    String m = scanner.next();
                    card.setChangeColoer(m);
                }
                room.action(room.getCurrentPlayer(), card,false);
            }



            show(room);

//            i = scanner.nextInt();
//            if(i == -1){
//                room.getGameInfo().action(room.getGameInfo().getCurrentPlayer(), null,true);
//            }else {
//                card = room.getGameInfo().getPlayerRudge(new Long(222)).get(i);
//                if (card.getFunc().equals(CardStatus.CRAD_FUNC_CHANGE)) {
//
//                }
//                room.getGameInfo().action(room.getGameInfo().getCurrentPlayer(), card, false);
//            }
//            show(room);
        }
    }


    public static void show(Room room){
        System.out.println("111："+room.getPlayerRudge(new Long(111)).size()+""+room.getPlayerRudge(new Long(111)));
        System.out.println("222: "+room.getPlayerRudge(new Long(222)).size()+""+room.getPlayerRudge(new Long(222)));
        System.out.println("333: "+room.getPlayerRudge(new Long(333)).size()+""+room.getPlayerRudge(new Long(333)));

        System.out.println("当前出牌玩家："+room.getCurrentPlayer().getPlayerId());
        System.out.println("上一张牌："+room.getPrevCard());
        System.out.println("摸牌堆："+room.getCardPile().size());
        System.out.println("弃牌堆："+room.getDiscardPile().size());
    }
}
