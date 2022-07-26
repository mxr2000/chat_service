package com.mxr.chatservice.component;


import com.mxr.chatservice.pojo.TalkPair;
import com.mxr.chatservice.pojo.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class Data {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Integer> heartbeatChances = new HashMap<>();
    private Map<String, TalkPair> usernameToTalkPairs = new HashMap<>();

    public Map<String, TalkPair> getUsernameToTalkPairs() {
        return usernameToTalkPairs;
    }

    public Map<String, Integer> getHeartbeatChances() {
        return heartbeatChances;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
