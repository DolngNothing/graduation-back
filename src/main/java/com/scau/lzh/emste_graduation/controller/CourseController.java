package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.Course;
import com.scau.lzh.emste_graduation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/api/course")
    public Course save(Course course) throws Exception {
        course=courseService.save(course);
        return course;
    }
}
