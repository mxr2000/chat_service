package com.mxr.chatservice.controller;

import com.mxr.chatservice.component.Data;
import com.mxr.chatservice.pojo.Message;
import com.mxr.chatservice.pojo.User;
import com.mxr.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private Data data;
    @RequestMapping("/sendmessage")
    public void sendMessageHandler(@RequestBody Message message){
        System.out.println(message);
        User user = data.getUsers().get(message.getReceiver());
        if(user == null){
            //对方不在线，将未处理消息放入数据库
            messageService.sendMessage(message, false);
        }else{
            //对方在线，将已处理消息放入数据库和消息队列
            messageService.sendMessage(message, true);
            user.getMessages().add(message);
        }
    }
    @RequestMapping("/acquirehistorymessages/{username}")
    public List<Message> acquireHistoryMessages(@PathVariable("username") String username){
        return messageService.getHistoryMessages(username);
    }
    @RequestMapping("/acquirehistorymessageswithcontact/{user1}/{user2}")
    public List<Message> acquireHistoryMessages(@PathVariable("user1") String user1,
                                                @PathVariable("user2") String user2){
        return messageService.getHistoryMessagesWithContact(user1, user2);
    }
    @RequestMapping("/acquireunhandledmessages/{username}")
    public List<Message> acquireUnhandledMessages(@PathVariable("username") String username){
        return messageService.getUnhandledMessages(username);
    }

    @RequestMapping("/echo")
    public List<Message> echo(){
        Message message = new Message("a", "b", "c", "d");
        List<Message> list = new ArrayList<>();
        list.add(message);
        return list;
    }


}
