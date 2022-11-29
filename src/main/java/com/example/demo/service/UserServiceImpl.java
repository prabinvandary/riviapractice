package com.example.demo.service;
import com.example.demo.mapper.UserDetailMapper;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.pojo.UserDetailRequestPojo;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserDetailMapper userDetailMapper;
    private final ObjectMapper objectMapper;
    private final StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }
    @Override
    public Object getUserByUserId(Integer userId) {
        return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found by given id"));
    }

    @Override
    public User getUserByUserName(String userName) {
        return  userRepo.findUserByUsername(userName);
    }


    @Override
    public void saveUserDetails(UserDetailRequestPojo userDetailRequestPojo) {
        User user = null;
        if (userDetailRequestPojo.getUserId()!= null)
            user = userRepo.findById(userDetailRequestPojo.getUserId()).orElse(new User());
        user = objectMapper.convertValue(userDetailRequestPojo, User.class);
        Student student = studentRepository.findById(userDetailRequestPojo.getStudentDetailId()).orElseThrow(() -> new RuntimeException("Student Detail Id Not Exist."));
        user.setPassword(passwordEncoder.encode(userDetailRequestPojo.getPassword()));
        user.setStudent(student);
        userRepo.save(user);
    }
    @Override
    public List<User> getUser() {
        return userRepo.findAll();
    }

}
