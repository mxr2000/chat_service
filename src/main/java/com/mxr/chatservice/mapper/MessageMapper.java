package com.mxr.chatservice.mapper;

import com.mxr.chatservice.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    void insertMessage(Message message);
    void insertUnhandledMessage(Message message);
    List<Message> getHistoryMessages(String username);
    List<Message> getUnhandledMessages(String username);
    void handleAllMessages(String username);
    List<Message> getHistoryMessagesWithContact(String user1, String user2);
}
