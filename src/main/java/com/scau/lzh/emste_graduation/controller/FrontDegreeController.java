package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.*;
import com.scau.lzh.emste_graduation.service.*;
import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static javax.swing.UIManager.get;

@RestController
public class FrontDegreeController {
    @Autowired
    private FrontDegreeService frontDegreeService;

    @Autowired
    private GraduateService graduateService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private UserService userService;

    @PutMapping("/api/frontDegree/{admissionId}")
    public Result save(@PathVariable("admissionId") String admissionId,
                            @RequestBody Graduate graduate) throws Exception {
        System.out.println(graduate.getGraduateDate());
        Student student = studentService.findById(admissionId);
        Major major=majorService.findById(graduate.getMajor().getId());
        School school= schoolService.getSchool(graduate.getSchool().getId());
        if (student!=null&&major!=null&&school!=null&&!graduate.getEducationLevel().equals("")&&graduate.getGraduateDate()!=null){

            List<Graduate> graduateList = graduateService.findByAdmissionId(admissionId);

            for(Graduate graduate1:graduateList){
                if(graduate1.getEducationLevel().equals(graduate.getEducationLevel())&&graduate1.getMajor().getId()==graduate.getMajor().getId()){
                    System.out.println("有相同学历了");
                    return Result.getFail("有相同学历了");
                }
            }

            if (frontDegreeService.findByAdmissionIdAndState(student.getAdmissionId(), 0).isEmpty() && frontDegreeService.findByAdmissionIdAndState(student.getAdmissionId(), 1).isEmpty()){
                FrontDegree frontDegree=new FrontDegree();
                graduate.setCertified(false);
                graduate.setMajor(major);
                graduate.setStudent(student);
                graduate= graduateService.save(graduate);

                frontDegree.setStudent(student);
                frontDegree.setGraduate(graduate);
                frontDegree.setState(0);
                frontDegree.setSubmitDate(new Date());
                frontDegree=frontDegreeService.save(frontDegree);
                return Result.getSuccess("成功");
            }
            System.out.println("已经有通过或者在审批的前置学历了");
            return Result.getFail("已经有通过或者在审批的前置学历了");
        }
        return Result.getFail("输入有误");
    }

    @GetMapping("/api/frontDegree/{admissionId}")
    public Result findByAdmissionId(@PathVariable("admissionId") String admissionId) throws Exception {
        List<FrontDegree> frontDegreeList=frontDegreeService.findByAdmissionId(admissionId);
        if(frontDegreeList==null || frontDegreeList.isEmpty()) Result.getFail("找不到你的前置学历申请");
        return Result.getSuccess("成功",frontDegreeList);
    }

    @GetMapping("/api/frontDegreeIng/{admissionId}")
    public Result findToCheckByAdmissionId(@PathVariable("admissionId") String admissionId) throws Exception {

        List<FrontDegree> byAdmissionIdAndState = frontDegreeService.findByAdmissionIdAndState(admissionId, 0);
        if(byAdmissionIdAndState==null||byAdmissionIdAndState.isEmpty()) return Result.getFail("找不到该学生的审批中的前置学历申请");
        FrontDegree frontDegree=byAdmissionIdAndState.get(0);
        return Result.getSuccess("成功",frontDegree);
    }

    @PutMapping("/api/frontDegree/{frontDegreeId}/{pass}/{admin}")
    public Result Update(@PathVariable("frontDegreeId")Long frontDegreeId,@PathVariable("pass")Boolean pass,@PathVariable("admin") String admin) throws Exception {
        FrontDegree frontDegree=frontDegreeService.findById(frontDegreeId);
        User user = userService.findByAccount(admin);
        if(frontDegree!=null){
            if(user == null) return Result.getFail("who are you");

            if(pass==true){
                frontDegree.setState(1);
                frontDegree.getGraduate().setCertified(true);
            }
            else
            {
                frontDegree.setState(0);
                frontDegree.getGraduate().setCertified(false);
            }
            frontDegree=frontDegreeService.save(frontDegree);
            return Result.getSuccess("成功");
        }
        return Result.getFail("找不到前置学历了！");
    }



}
