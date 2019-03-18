package com.qi.web.model.entity;

/**
 * @description:
 * @author: qigang
 * @create: 2019-03-18 20:21
 **/
public class RoomListDTO {
    private String id;
    private int persons;
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public RoomListDTO(String id, int persons, boolean status) {
        this.id = id;
        this.persons = persons;
        this.status = status;
    }

    public RoomListDTO() {
    }

    @Override
    public String toString() {
        return "RoomListDTO{" +
                "id=" + id +
                ", persons=" + persons +
                ", status=" + status +
                '}';
    }


}
