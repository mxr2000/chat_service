package com.mxr.chatservice.service;

import com.mxr.chatservice.pojo.Message;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessageService {
    Message sendMessage(String sender, String receiver, String type, String content, boolean handled);
    Message sendMessage(Message message, boolean handled);
    List<Message> getHistoryMessages(String username);
    List<Message> getUnhandledMessages(String username);
    List<Message> getHistoryMessagesWithContact(String user1, String user2);
}
