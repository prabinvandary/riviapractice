package com.example.demo.pojo;

import lombok.*;

import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StudentDetailRequestPojo {
    private Long id;
    private  String name;
    private  String email;
    private String address;
    private LocalDate dob;
    private Integer facultyId;
}
