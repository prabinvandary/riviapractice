package com.example.demo.Controller;

import com.example.demo.model.Faculty;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.pojo.ApiResponse;
import com.example.demo.pojo.FacultyDetailRequestPojo;
import com.example.demo.pojo.StudentDetailRequestPojo;
import com.example.demo.pojo.UserDetailRequestPojo;
import com.example.demo.service.FacultyService;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/student")

public class StudentController extends ApiResponse {
    private final StudentService studentService;
    private  final FacultyService facultyService;

    @Autowired
    public StudentController(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @GetMapping
    public List<Student> getstudent() {
        return studentService.getstudent();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    //with projection
    @GetMapping("getStudent/{id}")
    public ApiResponse getStudentById(@PathVariable(name = "id") Long id) {
        return success("Student data fetched successuflly", studentService.getStudentById(id));
    }

    //Same bata update ra save Day 2 Task
    @PostMapping("save")
    public ApiResponse saveStudentDetails(@RequestBody @Valid StudentDetailRequestPojo studentDetailRequestPojo) {
        studentService.saveStudentDetails(studentDetailRequestPojo);
        return success("Student saved sucessfully", null);
    }
}