package com.mxr.chatservice.controller;

import com.mxr.chatservice.component.AudioServer;
import com.mxr.chatservice.component.Data;
import com.mxr.chatservice.pojo.Message;
import com.mxr.chatservice.pojo.TalkPair;
import com.mxr.chatservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AudioController {
    @Autowired
    private Data data;
    @RequestMapping("/requestaudiochat/{username}/{receiver}")
    public void requestAudioChatHandler(HttpServletRequest request, @PathVariable("username")String username, @PathVariable("receiver")String receiver){
        TalkPair talkPair = new TalkPair();
        talkPair.setUsername1(username);
        talkPair.setIp1(request.getRemoteAddr());
        data.getUsernameToTalkPairs().put(username, talkPair);
        User user = data.getUsers().get(receiver);
        if(user != null){
            user.getMessages().add(new Message(username, receiver, "chatrequest", ""));
        }
    }
    @RequestMapping("/replyaudiochat/{username}/{requester}/{reply}")
    public void replyAudioChatHandler(HttpServletRequest request, @PathVariable("username")String username, @PathVariable("requester")String requester){
        TalkPair talkPair = data.getUsernameToTalkPairs().get(requester);
        if(talkPair != null){
            talkPair.setUsername2(username);
            talkPair.setIp2(request.getRemoteAddr());
            talkPair.setEstablished(true);
            data.getUsernameToTalkPairs().put(username, talkPair);
            data.getUsers().get(requester).getMessages().add(new Message(username, requester, "chatreply", "allow"));
        }
    }
    @RequestMapping("/stopaudiochat/{username}")
    public void endAudioChatHandler(@PathVariable("username")String username){
        TalkPair talkPair = data.getUsernameToTalkPairs().get(username);
        talkPair.setEstablished(false);
        String another = talkPair.getAnotherUsername(username);
        data.getUsernameToTalkPairs().remove(username);
        data.getUsernameToTalkPairs().remove(another);
        data.getUsers().get(another).getMessages().add(new Message(username, another, "chatover", ""));
    }
    @Autowired
    AudioServer audioServer;
    @RequestMapping("/testaudio/{sender}/{receiver}")
    public void testAudioHandler(HttpServletRequest request, @PathVariable("sender")String sender, @PathVariable("receiver")String receiver){
        audioServer.test();
        audioServer.getUsernameToIp().put(sender, request.getRemoteAddr());
        //回复
        if(audioServer.getUsernameToIp().containsKey(receiver)){
            String ip1 = audioServer.getUsernameToIp().get(sender);
            String ip2 = audioServer.getUsernameToIp().get(receiver);
            audioServer.getIpToip().put(ip1, ip2);
            audioServer.getIpToip().put(ip2, ip1);
        }else{
            //请求

        }
    }


}
