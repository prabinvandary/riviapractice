package com.example.demo.pojo;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class FacultyDetailRequestPojo {
    private Integer facultyId;
    @NotNull(message = "Faculty name cannot be null")
    @NotBlank(message = "Faculty name cannot be blank")
//    @Size(min = 5, max = 15, message = "User Name cannot be less than 5 or greater than 15")
    private String facultyName;

    private String facultyDescription;
    private String facultyDepartment;
    private  String facultyType;
}
