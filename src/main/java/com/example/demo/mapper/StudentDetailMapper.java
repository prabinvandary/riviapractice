package com.example.demo.mapper;


import com.example.demo.pojo.StudentDetailResponsePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentDetailMapper {
    @Select("select id, name, dob, age as \"studentName\" from student s where \"name\" = #{name}")
    StudentDetailResponsePojo getStudentDetailByName(String name);
}