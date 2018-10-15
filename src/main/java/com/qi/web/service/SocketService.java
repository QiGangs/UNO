package com.qi.web.service;

import com.qi.uno.model.entiy.Room;

import java.io.IOException;

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

}
