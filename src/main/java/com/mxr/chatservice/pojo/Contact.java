package com.mxr.chatservice.pojo;

import lombok.Data;

@Data
public class Contact {
    private String name;
    private String username;
    private String imgFormat;

    public Contact() {
    }

    public Contact(String name, String username, String imgFormat) {
        this.name = name;
        this.username = username;
        this.imgFormat = imgFormat;
    }
}
