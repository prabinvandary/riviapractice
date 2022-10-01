package com.example.demo.service;
import com.example.demo.mapper.UserDetailMapper;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.pojo.UserDetailRequestPojo;
import com.example.demo.pojo.UserDetailResponsePojo;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserDetailMapper userDetailMapper;
    private final ObjectMapper objectMapper;
    private final StudentRepository studentRepository;

    @Override
    public Object getUserByUserId(Integer userId) {
        return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found by given id"));

    }

    @Override
    public Object getUserByUserName(String userName) {
        UserDetailResponsePojo userDetailResponsePojo = userDetailMapper.getUserDetailByUserName(userName);
        return userDetailResponsePojo;
    }

//    @Override
//    public Object getUserById(Integer id) {
//        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found by given id"));
//
//    }
//
//    @Override
//    public Object getUserByName(String name) {
//        UserDetailResponsePojo userDetailResponsePojo = userDetailMapper.getUserDetailByUserName(name);
//        return userDetailResponsePojo;
//    }

    @Override
    public void saveUserDetails(UserDetailRequestPojo userDetailRequestPojo) {
        User user = null;
        if (userDetailRequestPojo.getUserId()!= null)
            user = userRepo.findById(userDetailRequestPojo.getUserId()).orElse(new User());
        user = objectMapper.convertValue(userDetailRequestPojo, User.class);
        Student student = studentRepository.findById(userDetailRequestPojo.getStudentDetailId()).orElseThrow(() -> new RuntimeException("Student Detail Id Not Exist."));
        user.setStudent(student);
        userRepo.save(user);
    }
    @Override
    public List<User> getUser() {
        return userRepo.findAll();
    }
}
