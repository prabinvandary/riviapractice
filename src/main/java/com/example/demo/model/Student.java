package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(generator = "student_details_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_details_gen", sequenceName = "student_details_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
    private String email;
    private String address;
    private LocalDate dob;
    @OneToOne
    @JoinColumn(name = "faculty_faculty_id")
    private Faculty faculty;

}
