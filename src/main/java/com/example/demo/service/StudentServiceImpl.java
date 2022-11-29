package com.example.demo.service;

import com.example.demo.mapper.StudentDetailMapper;
import com.example.demo.model.Faculty;
import com.example.demo.model.Student;
import com.example.demo.pojo.FacultyDetailRequestPojo;
import com.example.demo.pojo.StudentDetailRequestPojo;
import com.example.demo.pojo.StudentDetailResponsePojo;
import com.example.demo.projection.StudentDetailProjection;
import com.example.demo.repository.FacultyRepo;
import com.example.demo.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private  final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    private  final StudentDetailMapper studentDetailMapper;
    private  final FacultyRepo facultyRepo;
    private  final FacultyDetailRequestPojo facultyDetailRequestPojo;


    public StudentServiceImpl(StudentRepository studentRepository, ObjectMapper objectMapper, StudentDetailMapper studentDetailMapper, FacultyRepo facultyRepo, FacultyDetailRequestPojo facultyDetailRequestPojo) {

        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
        this.studentDetailMapper = studentDetailMapper;
        this.facultyRepo = facultyRepo;
        this.facultyDetailRequestPojo = facultyDetailRequestPojo;
    }
     @Override
    public List<Student> getstudent() {
       return studentRepository.findAll();
    }

@Override
    public void addNewStudent(Student student) {
        Optional<StudentDetailProjection> studentOptional=studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }
@Override
    public void deleteStudent(Long studentId) {
//        Optional<Student> studentOptional=studentRepository.findStudentByEmail(student.getEmail());
        Optional<StudentDetailProjection> exists=studentRepository.findStudentById(studentId);
        if (!exists.isPresent()){
            throw  new IllegalStateException(
                    "student with id"+ studentId + " does not exists"
            );
        }
        else {
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student=studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException(
                "student with id="+studentId+"doesnot exist"
        ));
        if (name!=null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if (email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            Optional<StudentDetailProjection> studentOptional=studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }

    @Override
    public Object getStudentById(Long id) {
        Student byId=studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student Not found by given id"));
        return null;
    }

    @Override
    public Object  getStudentByName(String name) {
        StudentDetailResponsePojo studentDetailResponsePojo = studentDetailMapper.getStudentDetailByName(name);
        return studentDetailResponsePojo;
    }

    @Override
    public void saveStudentDetails(StudentDetailRequestPojo studentDetailRequestPojo) {
        Student student=null;
        if (studentDetailRequestPojo.getId()!=null)

              student=studentRepository.findById(studentDetailRequestPojo.getId()).orElse(new Student());

        student=objectMapper.convertValue(studentDetailRequestPojo,Student.class);
       Faculty faculty = facultyRepo.findById(studentDetailRequestPojo.getFacultyId()).orElseThrow(() -> new RuntimeException("Student Detail Id Not Exist."));
            student.setFaculty(faculty);
                studentRepository.save(student);
    }
}
