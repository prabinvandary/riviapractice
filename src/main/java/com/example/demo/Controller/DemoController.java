package com.example.demo.Controller;

import com.example.demo.model.Student;
import com.example.demo.pojo.ApiResponse;
import com.example.demo.pojo.StudentDetailRequestPojo;
import com.example.demo.pojo.UserDetailRequestPojo;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")

public class DemoController  extends ApiResponse {
    private final StudentService studentService;
    private final UserService userService;

    @Autowired
    public DemoController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
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

    //    @PutMapping( "/{studentId}")
//    public  void updateStudent(
//            @PathVariable("studentId") Long studentId,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String email)
//    {
//             studentService.updateStudent(studentId,name,email);
//    }
    //Same bata update ra save Day 2 Task
    @PostMapping("save")
    public ApiResponse saveStudentDetails(@RequestBody StudentDetailRequestPojo studentDetailRequestPojo) {
        studentService.saveStudentDetails(studentDetailRequestPojo);
        return success("Student saved sucessfully", null);
    }


    @GetMapping("getUser/{userId}")
    public ApiResponse getUserById(@PathVariable(name = "userId") Integer userId) {
        return success("User data fetched successufly", userService.getUserByUserId(userId));
    }

    @GetMapping("getUserByUsername")
    public ApiResponse getUserByName(@RequestParam(name = "userName", required = false, defaultValue = "0") String UserName) {
        return success("User Date Fetched Successfully", userService.getUserByUserName(UserName));
    }

    @PostMapping("saveuser")
    public ApiResponse saveUserDetails(@RequestBody @Valid UserDetailRequestPojo userDetailRequestPojo) {
        userService.saveUserDetails(userDetailRequestPojo);
        return success("User details saved successfully", null);
    }
}
