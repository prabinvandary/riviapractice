package com.example.demo.service;
import com.example.demo.enums.UserType;
import com.example.demo.mapper.UserDetailMapper;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.pojo.UserDetailRequestPojo;
import com.example.demo.pojo.UserDetailResponsePojo;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final UserDetailMapper userDetailMapper;
    private final ObjectMapper objectMapper;
    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=  userRepo.findByUsername(username);
        if (user==null)
            throw new UsernameNotFoundException("Username not found");
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getUserType().name()));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }
    @Override
    public Object getUserByUserId(Integer userId) {
        return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found by given id"));

    }

    @Override
    public User getUserByUserName(String userName) {
        User user = userDetailMapper.getUserDetailByUserName(userName);
        return user;
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
        user.setPassword(passwordEncoder.encode(userDetailRequestPojo.getPassword()));
        userRepo.save(user);
    }
    @Override
    public List<User> getUser() {
        return userRepo.findAll();
    }


}
