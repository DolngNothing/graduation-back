package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.Course;
import com.scau.lzh.emste_graduation.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course save(Course course) throws Exception{
        try {
            course=courseRepository.saveAndFlush(course);
        }catch (Exception e){
            System.out.println("******课程保存失败******");
        }
        return course;
    }

    public Course findById(Long id) throws Exception{
        Course course=null;
        try {
            course=courseRepository.findById(id).orElse(null);
        }catch (Exception e){
            System.out.println("******课程查询失败******");
        }
        return course;
    }
}
