package com.example.demo.mapper;

import com.example.demo.pojo.StudentListPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface StudentMapper {
     @Select("select * from student where id = #{id}")
     StudentListPojo getSelectedData(Long id);
}
