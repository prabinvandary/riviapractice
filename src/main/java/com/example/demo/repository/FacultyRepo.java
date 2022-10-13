package com.example.demo.repository;

import com.example.demo.model.Faculty;
import com.example.demo.projection.FacultyDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FacultyRepo extends JpaRepository<Faculty, Integer> {

    @Query(value = "select * from faculty where facultyName = ?1", nativeQuery = true)
    List<Faculty> findFacultyByFacultyName(String facultyName);

    @Query(value = "select * from faculty order by facultyId desc", nativeQuery = true)
    List<Faculty> getList();

    @Query(value = "select * from faculty where facultyId = ?1", nativeQuery = true)
    Optional<Faculty> findFacultyById(Integer facultyId);

    @Query(value = "select * from faculty f where \"facultyName\" = ?1", nativeQuery = true)
    FacultyDetailProjection getFacultyByFacultyName(String facultyName);

}
