package com.qi.web.model;

/**
 * @description: Socket信息格式
 * @author: qigang
 * @create: 2018-10-15 13:56
 **/
public class Message {
    private Integer type;
    private Data data;

    private Message(Integer type, Data data) {
        this.type = type;
        this.data = data;
    }

    public static Message getMessage(Integer type, Data data){
        return new Message(type,data);
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}
