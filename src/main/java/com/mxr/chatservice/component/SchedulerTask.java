package com.mxr.chatservice.component;

import com.mxr.chatservice.pojo.Message;
import com.mxr.chatservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Configuration
@EnableScheduling
public class SchedulerTask {
    @Autowired
    private Data data;
    @Scheduled(fixedRate = 1000)
    private void configureTask(){
        List<String> offlineUsers = new ArrayList<>();
        for(String username : data.getHeartbeatChances().keySet()){
            int chances = data.getHeartbeatChances().get(username) - 1;
            data.getHeartbeatChances().put(username, chances);
            System.out.println(username + " " + chances);
            if(chances == 0){
                offlineUsers.add(username);
            }
        }
        //从数据中移除离线用户
        for(String username : offlineUsers){
            data.getHeartbeatChances().remove(username);
            data.getUsers().remove(username);
        }
        //对在线并且是联系人发送离线信息
        for(String offlineUsername : offlineUsers){
            for(String username : data.getUsers().keySet()){
                System.out.println("offline: " + offlineUsername);
                User user = data.getUsers().get(username);
                System.out.println(user.getContacts());
                if(user.containContact(offlineUsername)){
                    System.out.println("added");
                    user.getMessages().add(new Message(offlineUsername, user.getUsername(), "contactoffline", ""));
                }
            }
        }

    }
}
