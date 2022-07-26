package com.mxr.chatservice.service;

import com.mxr.chatservice.mapper.MessageMapper;
import com.mxr.chatservice.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public Message sendMessage(String sender, String receiver, String type, String content, boolean handled) {
        Message message = new Message(sender, receiver, type, content);
        return sendMessage(message, handled);
    }

    @Override
    public Message sendMessage(Message message, boolean handled) {
        if(handled){
            messageMapper.insertMessage(message);
        }else{
            messageMapper.insertUnhandledMessage(message);
        }
        return message;
    }

    @Override
    public List<Message> getHistoryMessages(String username) {
        List<Message> messages = messageMapper.getHistoryMessages(username);
        System.out.println("history = " + messages);
        System.out.println(messages);
        return messages;
    }

    @Override
    public List<Message> getUnhandledMessages(String username) {
        List<Message> messages = messageMapper.getUnhandledMessages(username);
        messageMapper.handleAllMessages(username);
        return messages;
    }

    @Override
    public List<Message> getHistoryMessagesWithContact(String user1, String user2) {
        return messageMapper.getHistoryMessagesWithContact(user1, user2);
    }
}
