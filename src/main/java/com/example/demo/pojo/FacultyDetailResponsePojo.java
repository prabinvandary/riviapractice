package com.example.demo.pojo;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyDetailResponsePojo {
    private Integer facyltyId;

    private String facultyName;

    private String facultyDescription;
    private String facultyDepartment;
    private  String facultyType;
}
