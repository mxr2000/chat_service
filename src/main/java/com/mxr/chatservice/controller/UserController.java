package com.mxr.chatservice.controller;

import com.mxr.chatservice.component.Data;
import com.mxr.chatservice.mapper.ContactMapper;
import com.mxr.chatservice.pojo.Contact;
import com.mxr.chatservice.pojo.LoginUser;
import com.mxr.chatservice.pojo.Message;
import com.mxr.chatservice.pojo.User;
import com.mxr.chatservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private Data data;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User loginHandler(@RequestBody LoginUser requestUser){
        System.out.println("username = " + requestUser.getUsername() + " " + requestUser.getPassword());
        User user = userService.login(requestUser.getUsername(), requestUser.getPassword());
        if(user != null){
            user.setContacts(contactMapper.getContactsByUsername(user.getUsername()));
            data.getUsers().put(user.getUsername(), user);
            data.getHeartbeatChances().put(user.getUsername(), 5);
            //对所有联系人，若在线，则发送联系人上线信息
            for (Contact contact : user.getContacts()){
                User contactUser = data.getUsers().get(contact.getUsername());
                if(contactUser != null){
                    contactUser.getMessages().add(new Message(user.getUsername(), "", "contactonlie", ""));
                }
            }
        }
        return user;
    }
    @RequestMapping("/signup")
    public User signupHandler(String username, String password, String imgFormat, String name){
        return userService.signup(username, password, imgFormat, name);

    }


}
