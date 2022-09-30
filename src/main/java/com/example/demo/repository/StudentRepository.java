package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.pojo.StudentDetailResponsePojo;
import com.example.demo.projection.StudentDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
@Query("SELECT s FROM  Student s WHERE s.email=?1")
    Optional<StudentDetailProjection> findStudentByEmail(String email);

@Query("SELECT s from Student s where s.id=?1")
    Optional<StudentDetailProjection> findStudentById(Long id);

@Query(value = "select id,name,dob,email from student s where  \"name\"=?1",nativeQuery = true)
StudentDetailResponsePojo getStudentByName(String name);

}
