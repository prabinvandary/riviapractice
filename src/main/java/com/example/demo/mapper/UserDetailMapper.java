package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDetailMapper {

    @Select("select user_id, user_name as \"userName\" from users u where \"user_name\" = #{userName}")
    User getUserDetailByUserName(String UserName);
}