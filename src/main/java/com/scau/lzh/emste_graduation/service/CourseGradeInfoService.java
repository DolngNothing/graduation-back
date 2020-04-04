package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.CourseGradeInfo;
import com.scau.lzh.emste_graduation.repository.CourseGradeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseGradeInfoService {
    @Autowired
    private CourseGradeInfoRepository courseGradeInfoRepository;

    @Autowired
    private StudentService studentService;

    public CourseGradeInfo save(CourseGradeInfo courseGradeInfo) throws Exception{
        try {
            courseGradeInfo=courseGradeInfoRepository.save(courseGradeInfo);
        }catch (Exception e){
            System.out.println("********保存成绩表出错********");
        }
        return courseGradeInfo;
    }

    public List<CourseGradeInfo> getList() throws Exception{
        List<CourseGradeInfo> courseGradeInfoList=null;
        try {
            courseGradeInfoList=courseGradeInfoRepository.findAll();
        }catch (Exception e){
            System.out.println("********找到所有成绩表出错********");
        }
        return courseGradeInfoList;
    }

    public List<CourseGradeInfo> findByAdmissionId(String admissionId) throws Exception{
        try {
            return courseGradeInfoRepository.findAllByStudent_AdmissionId(admissionId);
        } catch (Exception e) {
            System.out.println("********准考证找到成绩表出错********");
        }
        return null;
    }

}
