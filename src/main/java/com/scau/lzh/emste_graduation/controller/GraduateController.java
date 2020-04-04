package com.scau.lzh.emste_graduation.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scau.lzh.emste_graduation.domain.*;
import com.scau.lzh.emste_graduation.service.*;
import com.scau.lzh.emste_graduation.utils.GraduateParam;
import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class GraduateController {
    @Autowired
    private GraduateService graduateService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private GraduationApplyService graduationApplyService;

    @Autowired
    private FrontDegreeService frontDegreeService;

    @PostMapping("api/graduate/{admissionId}/{majorId}")
    public Graduate save(@PathVariable("admissionId") String admissionId,
                         @PathVariable("majorId") Long majorId,
                         @RequestBody Graduate graduate) throws Exception {
        Student student=null;
        student=studentService.findById(admissionId);
        Major major=majorService.findById(majorId);
        if(student!=null&&major!=null){
            graduate.setStudent(student);
            graduate.setMajor(major);
            graduate= graduateService.save(graduate);
            return graduate;
        }
        return null;
    }

    @GetMapping("api/graduates/year")
    public List<Graduate> findByYear(Integer year) throws Exception {
        List<Graduate> graduateList=graduateService.findByYear(year);
        return graduateList;
    }

    @GetMapping("api/graduates/id/{admissionId}")
    public Result findByAdmissionId(@PathVariable("admissionId") String admissionId) throws Exception {
        List<Graduate> graduateList=graduateService.findByAdmissionId(admissionId);
        if(graduateList == null || graduateList.isEmpty()) return Result.getFail("找不到毕业信息");
        return Result.getSuccess("找到",graduateList);
    }

    @DeleteMapping("api/graduate/{id}")
    public Result DeleteGraduate(@PathVariable("id") Long id) throws Exception {
        GraduationApply graduationApply=graduationApplyService.findByGraduateId(id);
        FrontDegree frontDegree = frontDegreeService.findByGraduateId(id);
        StringBuffer stringBuffer=new StringBuffer();
        Graduate graduate=graduateService.findById(id);
        if(graduate==null){
            System.out.println("不存在该毕业生");
            stringBuffer.append("不存在该毕业生");
            return  Result.getFail(stringBuffer.toString());
        }
        graduateService.delete(graduate);
        if(graduationApply!=null&&frontDegree==null){
            graduationApply.setGraduate(null);
            graduationApply.setCertificateState(4);
            graduationApplyService.save(graduationApply);
        }
        else if(frontDegree!=null&&graduationApply==null){
            stringBuffer.append("已经更新前置学历申请信息，");
            frontDegree.setGraduate(null);
            frontDegree.setState(3);
            frontDegreeService.save(frontDegree);
        }
        stringBuffer.append("已经更新毕业申请信息，删除成功！");
        return Result.getSuccess(stringBuffer.toString());
    }

    @PostMapping("api/graduate/query")
    public List<Graduate> query(@RequestBody GraduateParam graduateParam){
        List<Graduate> graduateList=graduateService.queryGraduate(graduateParam);
        return graduateList;
    }

    @GetMapping("api/graduates")
    public Result queryAll(){
        List<Graduate> graduateList=graduateService.queryGraduate(new GraduateParam());
        return Result.getSuccess("成功",graduateList);
    }

    @GetMapping("api/graduates1")
    public Result queryAll1(){
        List<Graduate> graduateList=graduateService.queryGraduate(new GraduateParam());
        return Result.getSuccess("成功",graduateList);
    }

    @GetMapping("api/test")
    public Result test() throws Exception {
        return Result.getSuccess("成功");
    }
}
