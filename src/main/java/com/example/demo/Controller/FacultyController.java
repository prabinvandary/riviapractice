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
@RequestMapping("api/faculty")

public class FacultyController extends ApiResponse {
    private  final FacultyService facultyService;

    @Autowired
    public FacultyController( FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("getfaculty/{facultyId}")
    public ApiResponse getFacultyById(@PathVariable(name = "facultyId") Integer facultyId) {
        return success("User data fetched successufly",facultyService.getFacultyById(facultyId));
    }

    @GetMapping("getFacultyByName")
    public ApiResponse getFacultyByName(@RequestParam(name = "userName", required = false, defaultValue = "0") String facultyName) {
        return success("User Date Fetched Successfully", facultyService.getFacultyByName(facultyName));
    }

    @PostMapping()
    public ApiResponse saveFacultyDetails(@RequestBody @Valid FacultyDetailRequestPojo facultyDetailRequestPojo) {
        facultyService.saveFacultyDetails(facultyDetailRequestPojo);
        return success("Faculty details saved successfully", null);
    }
    @GetMapping()
    public List<Faculty> getFaculty() {
        return facultyService.getFaculty();
    }

}