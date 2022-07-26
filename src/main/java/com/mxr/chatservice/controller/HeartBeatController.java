package com.mxr.chatservice.controller;

import com.mxr.chatservice.component.Data;
import com.mxr.chatservice.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HeartBeatController {
    @Autowired
    private Data data;
    @RequestMapping("/heartbeat/{username}")
    List<Message> acquireMessagesHandler(@PathVariable("username") String username){
        //设置chances
        data.getHeartbeatChances().put(username, 5);
        //获取，清空，返回消息
        List<Message> messages = data.getUsers().get(username).getMessages();
        List<Message> ret = new ArrayList<>();
        for(Message message : messages){
            ret.add(message);
        }
        messages.clear();
        return ret;
    }
}
