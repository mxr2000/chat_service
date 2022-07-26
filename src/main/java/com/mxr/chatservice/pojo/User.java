package com.mxr.chatservice.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
public class User {
    private String name;
    private String username;
    private String imgFormat;
    private String password;
    private List<Contact> contacts = new ArrayList<>();
    private List<Message> messages = new LinkedList<>();

    public boolean containContact(String name){
        for(Contact contact : contacts){
            if(contact.getUsername().equals(name)){
                return true;
            }
        }
        return false;
    }
}
