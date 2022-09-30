package com.example.demo.service;

import com.example.demo.pojo.UserDetailRequestPojo;
import com.example.demo.pojo.ApiResponse;
import com.example.demo.pojo.UserDetailResponsePojo;

public interface UserService {
    Object getUserByUserId(Integer userId);

    Object getUserByUserName(String userName);


    void saveUserDetails(UserDetailRequestPojo userDetailRequestPojo);
}