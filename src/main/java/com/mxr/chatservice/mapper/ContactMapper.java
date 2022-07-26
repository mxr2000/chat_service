package com.mxr.chatservice.mapper;

import com.mxr.chatservice.pojo.Contact;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContactMapper {
    void insertContact(String user, String contact);
    List<Contact> getContactsByUsername(String username);

}
