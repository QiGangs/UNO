package com.qi.web.service;

import com.qi.uno.model.entiy.Player;
import com.qi.uno.model.entiy.Room;
import com.qi.web.model.Playermini;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @description:
 * @author: qigang
 * @create: 2018-10-15 21:17
 **/
public interface SocketService {
     String getRoomId();

     void sendRoomInfoBackClient(Room room)throws IOException, InterruptedException;

     void sendRoomMissToAll(Room room)throws IOException, InterruptedException;

     Boolean isCanStartGame(Room room);

     Boolean startGame(Room room);

     void sendStartGameToAll(Room room) throws IOException, InterruptedException;

     void sendRoomInfoToAll(Room room) throws IOException, InterruptedException;

     void dealPutCardAction(Room room,Object info)throws IOException, InterruptedException;

     void sendGameoverToAll(Room room,Player winer) throws IOException, InterruptedException;

     ArrayList<Playermini> getPlayerIdList(Room room);

}
