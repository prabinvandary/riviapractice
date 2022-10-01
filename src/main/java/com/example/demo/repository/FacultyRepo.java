//package com.example.demo.repository;
//
//import com.example.demo.model.Faculty;
//import com.example.demo.model.User;
//import com.example.demo.pojo.FacultyDataProjection;
//import com.example.demo.pojo.UserDataProjection;
//import com.example.demo.projection.FacultyDetailProjection;
//import com.example.demo.projection.UserDetailProjection;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface FacultyRepo extends JpaRepository<Faculty, Integer> {
//
//    @Query(value = "select * from faculty where facultyName = ?1", nativeQuery = true)
//    List<Faculty> findFacultyByFacultyName(String facultyName);
//
//    @Query(value = "select * from faculty order by facultyId desc", nativeQuery = true)
//    List<Faculty> getList();
//
//    @Query(value = "select * from faculty where facultyId = ?1", nativeQuery = true)
//    FacultyDataProjection getFacultyByFacultyId(Integer facultyId);
//
//    @Query(value = "select * from faculty f where \"facultyName\" = ?1", nativeQuery = true)
//    FacultyDetailProjection getFacultyByFacultyName(String facultyName);
//}
