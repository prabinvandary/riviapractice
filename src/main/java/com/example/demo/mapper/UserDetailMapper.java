package com.example.demo.mapper;

import com.example.demo.pojo.UserDetailResponsePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDetailMapper {

    @Select("select userId, UserName as \"userName\" from users u where \"userName\" = #{userName}")
    UserDetailResponsePojo getUserDetailByUserName(String UserName);
}