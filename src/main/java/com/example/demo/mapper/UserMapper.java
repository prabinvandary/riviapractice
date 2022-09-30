package com.example.demo.mapper;


import com.example.demo.pojo.UserDataProjection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from users where userId = #{userId}")
    UserDataProjection getSelectedData(Integer userId);
}