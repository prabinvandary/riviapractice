package com.example.demo.Controller;
import com.example.demo.model.User;
import com.example.demo.pojo.ApiResponse;
import com.example.demo.pojo.UserDetailRequestPojo;
import com.example.demo.service.FacultyService;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")

public class UserController extends ApiResponse {
    private final UserService userService;

    @Autowired
    public UserController(StudentService studentService, UserService userService, FacultyService facultyService) {
        this.userService = userService;
    }

    @GetMapping("getUser/{userId}")
    public ApiResponse getUserById(@PathVariable(name = "userId") Integer userId) {
        return success("User data fetched successufly", userService.getUserByUserId(userId));
    }

    @GetMapping("getUserByUsername/{userName}")
    public ApiResponse getUserByName(@RequestParam(name = "userName", required = false, defaultValue = "0") String UserName) {
        return success("User Date Fetched Successfully", userService.getUserByUserName(UserName));
    }

    @PostMapping()
    public ApiResponse saveUserDetails(@RequestBody @Valid UserDetailRequestPojo userDetailRequestPojo) {
        userService.saveUserDetails(userDetailRequestPojo);
        return success("User details saved successfully", null);
    }
    @GetMapping()
    public List<User> getUser() {
        return userService.getUser();
    }
}