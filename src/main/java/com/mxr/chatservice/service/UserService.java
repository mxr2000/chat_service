package com.mxr.chatservice.service;

import com.mxr.chatservice.pojo.User;

public interface UserService {
    User login(String username, String password);
    User signup(String username, String password, String imgFormat, String name);
}
