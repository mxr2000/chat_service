package com.mxr.chatservice.pojo;

import lombok.Data;

@Data
public class Message {
    private int id;
    private String sender;
    private String receiver;
    private String type;
    private String content;

    public Message(String sender, String receiver, String type, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.content = content;
    }
}
