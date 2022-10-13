package com.example.demo.model;

import com.example.demo.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "faculty")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Faculty {
    @Id
    @SequenceGenerator(sequenceName = "faculty_seq_gen", name = "faculty_seq", allocationSize = 1)
    @GeneratedValue(generator = "faculty_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer facultyId;

    @Column(name = "facultyName")
    @NotNull
    private String facultyName;

    private String facultyDescription;
    private String facultyDepartment;
    private  String facultyType;
       }
