package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.pojo.ApiResponse;
import com.example.demo.pojo.StudentDetailRequestPojo;
import com.example.demo.pojo.StudentDetailResponsePojo;

import java.util.List;

public interface StudentService {
    Object getStudentById(Long id);
    Object getStudentByName(String name);


    public List<Student> getstudent();
    public void addNewStudent(Student student);
    public void deleteStudent(Long studentId);
    void saveStudentDetails(StudentDetailRequestPojo studentDetailRequestPojo);
}
