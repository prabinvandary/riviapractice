package com.example.demo.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "student", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "UNIQUE_student_email")})
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName="student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
private Long id;

    @Column(name = "name")
    @NotNull
    private  String name;

    @Column(name="email")
    @NotNull
    private  String email;
    private LocalDate dob;
    @Transient
    private  Integer age;

    public Student() {

    }

    public Student(Long id, String name,
                   String email,
                   LocalDate dob
                   ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
//    @OneToOne
//    @JoinColumn(name = "facultyId", referencedColumnName = "facultyId", foreignKey = @ForeignKey(name = "FK_student_faculty"))
//    private Faculty faculty;
}
