package com.example.demo.service;

import com.example.demo.mapper.FacultyDetailMapper;
import com.example.demo.model.Faculty;
import com.example.demo.pojo.FacultyDetailRequestPojo;
import com.example.demo.pojo.FacultyDetailResponsePojo;
import com.example.demo.repository.FacultyRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FacultyServiceImpl<facultyDetailRequestPojo> implements FacultyService {
    private final FacultyRepo facultyRepo;
    private final FacultyDetailMapper facultyDetailMapper;
    private final ObjectMapper objectMapper;
    private final FacultyDetailRequestPojo facultyDetailRequestPojo;

    public FacultyServiceImpl(FacultyRepo facultyRepo, FacultyDetailMapper facultyDetailMapper, ObjectMapper objectMapper, FacultyDetailRequestPojo facultyDetailRequestPojo) {
        this.facultyRepo = facultyRepo;
        this.facultyDetailMapper = facultyDetailMapper;
        this.objectMapper = objectMapper;
        this.facultyDetailRequestPojo = facultyDetailRequestPojo;
    }

    @Override
    public Object getFacultyById(Integer facultyId) {
        return facultyRepo.findById(facultyId).orElseThrow(() -> new RuntimeException("Faculty not found by given id"));
    }

    @Override
    public Object getFacultyByName(String facultyName) {
        FacultyDetailResponsePojo facultyDetailResponsePojo = facultyDetailMapper.getFacultyDetailByFacultyName(facultyName);
        return facultyDetailResponsePojo;
    }

    @Override
    public List<Faculty> getFaculty() {
        return facultyRepo.findAll();
    }

    @Override
    public void saveFacultyDetails(FacultyDetailRequestPojo facultyDetailRequestPojo) {
        Faculty faculty = null;
        if (facultyDetailRequestPojo.getFacultyId()!= null)
            faculty = facultyRepo.findById(facultyDetailRequestPojo.getFacultyId()).orElse(new Faculty());
        faculty = objectMapper.convertValue(facultyDetailRequestPojo, Faculty.class);
        System.out.println( faculty);
        facultyRepo.save(faculty);
    }
}
