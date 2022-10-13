package com.example.demo.pojo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDetailResponsePojo {
    private Long id;
    private  String name;
    private  String email;
    private LocalDate dob;
    private  int age;
    private  String address;
    private Integer facultyId;
}

