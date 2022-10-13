package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.pojo.UserDetailRequestPojo;
import com.example.demo.pojo.ApiResponse;
import com.example.demo.pojo.UserDetailResponsePojo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Object getUserByUserId(Integer userId);

    Object getUserByUserName(String userName);

    public List<User> getUser();
    void saveUserDetails(UserDetailRequestPojo userDetailRequestPojo);
    Optional<User> findById(Integer id);

}