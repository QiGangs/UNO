package com.qi.uno.model.entiy;

/**
 * @description: 描述每张牌
 * @author: qigang
 * @create: 2018-07-05 11:00
 **/
public class Card {
    private int id;
    private String type;
    private int num;
    private String func;
    private String color;

//    private String changeColoer;


    public Card(int id, String type, int num, String func, String color) {
        this.id = id;
        this.type = type;
        this.num = num;
        this.func = func;
        this.color = color;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getType() {
        return type;
    }

//    public void setType(String type) {
//        this.type = type;
//    }

    public int getNum() {
        return num;
    }

//    public void setNum(int num) {
//        this.num = num;
//    }

    public String getFunc() {
        return func;
    }

//    public void setFunc(String func) {
//        this.func = func;
//    }

    public String getColor() {
        return color;
    }

//    public void setColor(String color) {
//        this.color = color;
//    }


//    public String getChangeColoer() {
//        return changeColoer;
//    }
//
//    public void setChangeColoer(String changeColoer) {
//        this.changeColoer = changeColoer;
//    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", num=" + num +
                ", func='" + func + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
