package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.Course;
import com.scau.lzh.emste_graduation.domain.Major;
import com.scau.lzh.emste_graduation.repository.MajorRepository;
import com.scau.lzh.emste_graduation.service.CourseService;
import com.scau.lzh.emste_graduation.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.util.*;

@RestController
public class MajorController {

    @Autowired
    private MajorService majorService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/api/major")
    public Major save(Major major) throws Exception {
       major=majorService.save(major);
       return major;
    }

    /**
     * 这个功能说是修改专业，其实就是修改毕业条件
     * @return
     */
    @PostMapping("/api/major/{id}")
    public Major setCourses(@PathVariable("id") Long id, @RequestParam List<Long> courseIdList) throws Exception {
        Major major=majorService.findById(id);
        if(major!=null){
            Course course=null;
            List<Course> courseList=new ArrayList<Course>();

            for (Long cid:courseIdList){
                course=courseService.findById(cid);
                if(course!=null){
                    courseList.add(course);
                }
            }
            major.setCourseList(courseList);
            major=majorService.save(major);
        }
        return major;
    }
}
