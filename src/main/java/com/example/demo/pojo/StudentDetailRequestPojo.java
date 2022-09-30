package com.example.demo.pojo;

import lombok.*;

import javax.persistence.Transient;
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
    private LocalDate dob;
}
