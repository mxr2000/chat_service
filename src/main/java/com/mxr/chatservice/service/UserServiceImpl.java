package com.mxr.chatservice.service;

import com.mxr.chatservice.mapper.UserMapper;
import com.mxr.chatservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(String username, String password) {
        return userMapper.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public User signup(String username, String password, String imgFormat, String name) {
        if(userMapper.getUserByUsername(username) != null){
            return null;
        }
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setImgFormat(imgFormat);
        user.setPassword(password);
        userMapper.insertUser(user);
        return user;
    }
}
