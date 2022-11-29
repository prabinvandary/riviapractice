package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.pojo.UserDetailRequestPojo;
import com.example.demo.pojo.ApiResponse;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Object getUserByUserId(Integer userId);


    @Select("select user_name as \"username\",password as \"password\" from users  where user_name = #{userName}")
    User getUserByUserName(String userName);

    public List<User> getUser();
    void saveUserDetails(UserDetailRequestPojo userDetailRequestPojo);
    Optional<User> findById(Integer id);

}