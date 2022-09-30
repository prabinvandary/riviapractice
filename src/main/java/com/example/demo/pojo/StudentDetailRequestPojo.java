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
    @Pattern(regexp = "^[A-Za-z]+[A-Za-z ]*$",message = "Enter only strings")
    private  String name;
    private  String email;
    private LocalDate dob;
}
