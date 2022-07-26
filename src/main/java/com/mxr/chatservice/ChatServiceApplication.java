package com.mxr.chatservice;

import com.mxr.chatservice.component.AudioServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatServiceApplication {
    public static void main(String[] args) {


        SpringApplication.run(ChatServiceApplication.class, args);
    }

}
