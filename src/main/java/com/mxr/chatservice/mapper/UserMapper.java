package com.mxr.chatservice.mapper;

import com.mxr.chatservice.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();
    User getUserByUsername(String username);
    User getUserByUsernameAndPassword(String username, String password);
    void insertUser(User user);
}
