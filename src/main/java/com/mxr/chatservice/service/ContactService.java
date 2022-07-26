package com.mxr.chatservice.service;

import com.mxr.chatservice.pojo.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContactsByUsername(String username);
    void addContact(String username1, String username2);
}
