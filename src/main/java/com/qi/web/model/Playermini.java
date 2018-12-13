package com.qi.web.model;

/**
 * @description:
 * @author: qigang
 * @create: 2018-12-07 14:57
 **/
public class Playermini {
    private String id;
    private int num;

    public Playermini(String id, int num) {
        this.id = id;
        this.num = num;
    }

    public static Playermini getinstance(String id, int num){
        return new Playermini(id,num);
    }

    public Playermini() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Playermini{" +
                "id='" + id + '\'' +
                ", num=" + num +
                '}';
    }
}
