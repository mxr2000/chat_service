package com.mxr.chatservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MianController {
    @RequestMapping("/index")
    public String mainHandler(){
        return "index";
    }
}
