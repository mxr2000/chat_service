package com.mxr.chatservice.controller;

import com.mxr.chatservice.component.Data;
import com.mxr.chatservice.pojo.Contact;
import com.mxr.chatservice.pojo.Message;
import com.mxr.chatservice.pojo.User;
import com.mxr.chatservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private Data data;

    @RequestMapping("/addcontactrequest")
    public void addContactRequestHandler(@RequestBody Message message){
        data.getUsers().get(message.getReceiver()).getMessages().add(message);
    }
    @RequestMapping("/addcontactreply")
    public void addContactReplyHandler(@RequestBody Message message){
        contactService.addContact(message.getSender(), message.getReceiver());
        contactService.addContact(message.getReceiver(), message.getSender());
        data.getUsers().get(message.getReceiver()).getMessages().add(message);
    }
    @RequestMapping("/addcontact/{receiver}")
    public Contact acquireContactHandler(@PathVariable("receiver") String receiver){
        User user = data.getUsers().get(receiver);
        return new Contact(user.getName(), user.getUsername(), user.getImgFormat());
    }
    @RequestMapping("/acquireallcontacts/{username}")
    public List<Contact> acquireAllContactsHandler(@PathVariable("username") String username){
        System.out.println("acquire username = " + username);
        User user = data.getUsers().get(username);
        return user.getContacts();
    }



}
