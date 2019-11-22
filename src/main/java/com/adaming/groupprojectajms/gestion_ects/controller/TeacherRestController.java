package com.adaming.groupprojectajms.gestion_ects.controller;

import com.adaming.groupprojectajms.gestion_ects.dto.TeacherDto;
import com.adaming.groupprojectajms.gestion_ects.entity.Teacher;
import com.adaming.groupprojectajms.gestion_ects.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gestion_ects")
public class TeacherRestController{

    @Autowired
    private TeacherService teacherService;

    @GetMapping(value="/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeacherDto> getTeachers(){
        Iterable<Teacher> teachers=this.teacherService.fetchAll();
        List<TeacherDto> teachersDto=new ArrayList<>();
        for (Teacher t:teachers) {
            teachersDto.add(t.toDto());
        }
        return teachersDto;
    }

    @GetMapping(value="/teacher/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDto getTeacher(@PathVariable("id") Long id){
        Teacher teacher=this.teacherService.fetchById(id);
        teacher.setCourses(teacher.getCourses().stream().distinct().collect(Collectors.toList()));
        return teacher.toDto();
    }

    public TeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
