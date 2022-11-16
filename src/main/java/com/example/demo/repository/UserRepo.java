package com.example.demo.repository;
import com.example.demo.model.User;
import com.example.demo.pojo.UserDataProjection;
import com.example.demo.pojo.UserDetailResponsePojo;
import com.example.demo.projection.UserDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select * from users where userName = ?1", nativeQuery = true)
    User findByUsername(String userName);

    @Query(value = "select * from users order by id desc", nativeQuery = true)
    List<User> getList();

    @Query(value = "select userName from users where id = ?1", nativeQuery = true)
    UserDataProjection getSelectedData(Integer id);

    @Query(value = "select id, userName as \"userName\" from users u where \"userName\" = ?1", nativeQuery = true)
    UserDetailProjection getUserByUserName(String userName);
    @Query(value = "select * from users where id = ?1", nativeQuery = true)
    UserDataProjection findUserByUserId(Integer id);
}