package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.CEgradeInfo;
import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.service.CEgradeInfoService;
import com.scau.lzh.emste_graduation.service.StudentService;
import com.scau.lzh.emste_graduation.utils.Result;
import jdk.nashorn.internal.runtime.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CEgradeInfoController {
    @Autowired
    private CEgradeInfoService cEgradeInfoService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/api/ceGradeInfo")
    public String save(Double chinese,Double english,String admissionId) throws Exception {
        Student student=studentService.findById(admissionId);
        if(student!=null){
            CEgradeInfo cEgradeInfo=new CEgradeInfo();
            cEgradeInfo.setChineseGrade(chinese);
            cEgradeInfo.setEnglishGrade(english);
            cEgradeInfo=cEgradeInfoService.save(cEgradeInfo);
            student.setcEgradeInfo(cEgradeInfo);
            studentService.save(student);
            return "success";
        }
        else {
            return "fail";
        }
    }

    @GetMapping("/api/ceGradeInfos")
    public List<CEgradeInfo> getList() throws Exception {
        List<CEgradeInfo> cEgradeInfoList=cEgradeInfoService.cEgradeInfoList();
        return cEgradeInfoList;
    }

    @DeleteMapping("/api/ceGradeInfo/{admissionId}/{id}")
    public void deleteCegrade(@PathVariable("admissionId") String admissionId,@PathVariable("id") Long id) throws Exception {
        Student student=studentService.findById(admissionId);
        if(student!=null){
            student.setcEgradeInfo(null);
            studentService.save(student);
        }
        cEgradeInfoService.deleteById(id);
    }

    @GetMapping("/api/ceGradeInfo/{admissionId}/{city}/{dis}")
    public Result getCeGradeInfo(@PathVariable("admissionId") String id,@PathVariable(value = "city",required = false) Long city,@PathVariable(value = "dis",required = false) Long dis)throws Exception{
        CEgradeInfo cEgradeInfo=cEgradeInfoService.findByAdmissionId(id);
        if(cEgradeInfo==null){
            return Result.getFail("没有中英文成绩表");
        }
        Student student = studentService.findById(id);
        if(city!=99&&dis!=99&&(city!=student.getUser().getCity().getId()||dis!=student.getUser().getDistrict().getId())) return Result.getFail("区考试只能打印该区的学生的中英文成绩");
        if(city!=99&&city!=student.getUser().getCity().getId()) return Result.getFail("市考试只能打印本市学生的中英文成绩");
        return Result.getSuccess("成功",cEgradeInfo);
    }
}
