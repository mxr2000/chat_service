package com.mxr.chatservice.service;

import com.mxr.chatservice.mapper.ContactMapper;
import com.mxr.chatservice.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactMapper contactMapper;
    @Override
    public List<Contact> getContactsByUsername(String username) {
        return contactMapper.getContactsByUsername(username);
    }

    @Override
    public void addContact(String username1, String username2) {
        contactMapper.insertContact(username1, username2);
        contactMapper.insertContact(username2, username1);
    }
}
