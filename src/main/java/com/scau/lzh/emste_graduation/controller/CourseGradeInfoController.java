package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.Course;
import com.scau.lzh.emste_graduation.domain.CourseGradeInfo;
import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.service.CourseGradeInfoService;
import com.scau.lzh.emste_graduation.service.CourseService;
import com.scau.lzh.emste_graduation.service.StudentService;
import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;
import java.util.List;

@RestController
public class CourseGradeInfoController {
    @Autowired
    private CourseGradeInfoService courseGradeInfoService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/api/courseGrade/{courseId}/{admissionId}")
    public CourseGradeInfo save(CourseGradeInfo courseGradeInfo,
                                @PathVariable("courseId") Long courseId,
                                @PathVariable("admissionId")String admissionId) throws Exception {
        Course course=courseService.findById(courseId);
        Student student=studentService.findById(admissionId);
        if(course!=null&&student!=null) {
            courseGradeInfo.setCourse(course);
            courseGradeInfo.setStudent(student);
            courseGradeInfo=courseGradeInfoService.save(courseGradeInfo);
            return courseGradeInfo;
        }
        return null;
    }

    @GetMapping("/api/courseGrades")
    public List<CourseGradeInfo> getList() throws Exception {
        List<CourseGradeInfo> courseGradeInfoList=courseGradeInfoService.getList();
        return courseGradeInfoList;
    }

    @GetMapping("/api/courseGrades/{admissionId}/{city}/{dis}")
    public Result getListByAdmissionId(@PathVariable("admissionId") String id,@PathVariable(value = "city",required = false) Long city,@PathVariable(value = "dis",required = false) Long dis)throws Exception{
        List<CourseGradeInfo> courseGradeInfoList=courseGradeInfoService.findByAdmissionId(id);
        if(courseGradeInfoList==null||courseGradeInfoList.isEmpty()) return Result.getFail("没有任何成绩表");
        Student student = studentService.findById(id);
        if(city!=99&&dis!=99&&(city!=student.getUser().getCity().getId()||dis!=student.getUser().getDistrict().getId())) return Result.getFail("区考试只能打印该区的学生的中英文成绩");
        if(city!=99&&city!=student.getUser().getCity().getId()) return Result.getFail("市考试只能打印本市学生的中英文成绩");
        return Result.getSuccess("成功",courseGradeInfoList);
    }

}
