package com.scau.lzh.emste_graduation.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scau.lzh.emste_graduation.domain.*;
import com.scau.lzh.emste_graduation.service.*;
import com.scau.lzh.emste_graduation.utils.DistributeParam;
import com.scau.lzh.emste_graduation.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class GraduationApplyController {
    @Autowired
    private GraduationApplyService graduationApplyService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private GraduateService graduateService;

    @Autowired
    private CourseGradeInfoService courseGradeInfoService;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private WithHoldingGraduationService withHoldingGraduationService;

    @PostMapping("/api/graduationApply/{admissionId}/{majorId}")
    public Result save(@RequestBody Date graduateDate,
                       @PathVariable("admissionId") String admissionId,
                       @PathVariable("majorId") Long majorId) throws Exception {
        if(graduateDate == null) return Result.getFail("没有填写日期");
        Student student=studentService.findById(admissionId);
        Major major=majorService.findById(majorId);
        Boolean pass=true;
        StringBuffer result=new StringBuffer();

        if(student==null||major==null){
            System.out.println("**********学生或专业不存在********");
            result.append("学生或专业不存在"+"\n");
            pass=false;
        }

        List<Graduate> graduateList= graduateService.findByAdmissionId(admissionId);
        for(Graduate graduate:graduateList){
            if(graduate.getMajor().getId()==majorId&&graduate.getEducationLevel().equals(student.getEducation())){
                System.out.println("*******同等学历专业毕业重复了********"+"\n");
                result.append("同等学历专业毕业重复了"+"\n");
                pass=false;
            }
        }

        if(!graduationApplyService.findByAdmissIdAndApplyState(admissionId,0).isEmpty()||
                !graduationApplyService.findByAdmissIdAndApplyState(admissionId,1).isEmpty()||
                !graduationApplyService.findByAdmissIdAndApplyState(admissionId,2).isEmpty()
        ){
            pass=false;
            result.append("已经有申请在处理了"+"\n");
        }

        WithHoldingGraduationController withHoldingGraduationController=new WithHoldingGraduationController();

        if(withHoldingGraduationController.isAbleToGraduate(student,major,graduateService,majorService,courseGradeInfoService,thesisService,result)&&pass){
            GraduationApply graduationApply=new GraduationApply();
            graduationApply.setSubmitDate(new Date());
            graduationApply.setApplyState(0);
            graduationApply.setMajor(major);
            graduationApply.setStudent(student);
            graduationApply.setGraduateDate(graduateDate);
            graduationApply=graduationApplyService.save(graduationApply);
            result.append("申请成功!");
        }
        else{
            result.append("毕业资格没到"+"\n");
            pass=false;
        }
        if(pass){
            return Result.getSuccess(result.toString());
        }
        else {
            return Result.getFail(result.toString());
        }
    }


    @PostMapping("/api/graduationApply/queryQualify/{admissionId}/{majorId}")
    public Result queryQualify(@RequestBody Date graduateDate,
                       @PathVariable("admissionId") String admissionId,
                       @PathVariable("majorId") Long majorId) throws Exception {
        System.out.println("#########"+graduateDate);

        if(graduateDate == null) return Result.getFail("没有填写日期");

        Student student=studentService.findById(admissionId);
        Major major=majorService.findById(majorId);
        Boolean pass=true;
        StringBuffer result=new StringBuffer();

        if(student==null||major==null){
            System.out.println("**********学生或专业不存在********");
            result.append("学生或专业不存在"+"\n");
            pass=false;
        }

        List<Graduate> graduateList= graduateService.findByAdmissionId(admissionId);
        for(Graduate graduate:graduateList){
            if(graduate.getMajor().getId()==majorId&&graduate.getEducationLevel().equals(student.getEducation())){
                System.out.println("*******同等学历专业毕业重复了********"+"\n");
                result.append("同等学历专业毕业重复了"+"\n");
                pass=false;
            }
        }

        if(!graduationApplyService.findByAdmissIdAndApplyState(admissionId,0).isEmpty()||
                !graduationApplyService.findByAdmissIdAndApplyState(admissionId,1).isEmpty()||
                !graduationApplyService.findByAdmissIdAndApplyState(admissionId,2).isEmpty()
        ){
            pass=false;
            result.append("已经有申请在处理了"+"\n");
        }

        WithHoldingGraduationController withHoldingGraduationController=new WithHoldingGraduationController();

        if(withHoldingGraduationController.isAbleToGraduate(student,major,graduateService,majorService,courseGradeInfoService,thesisService,result)){
            result.append("已有申请毕业的资格!");
        }
        else{
            result.append("毕业资格没到"+"\n");
            pass=false;
        }
        if(pass){
            return Result.getSuccess(result.toString());
        }
        else {
            return Result.getFail(result.toString());
        }
    }

    @GetMapping("/api/graduationApply/{admissionId}")
    public Result findByAdmissionId(@PathVariable("admissionId") String admissionId) throws Exception {
        List<GraduationApply> graduationApplyList= graduationApplyService.findByAdmissId(admissionId);
        if(graduationApplyList==null||graduationApplyList.isEmpty()) return Result.getFail("找不到任何申请记录");
        return Result.getSuccess("成功",graduationApplyList);
    }

    @GetMapping("/api/graduationApply/approval/{city}/{district}")
    public Result findList(@PathVariable("city") Long city,@PathVariable("district") Long district) throws Exception {
        List<GraduationApply> graduationApplyList=null;
        if(city==99&&district==99){
            graduationApplyList=graduationApplyService.findByState(2);
        }
        else if(district==99){
            graduationApplyList=graduationApplyService.findByStateAndCity(1,city);
        }else if(city!=99&&district!=99){
            graduationApplyList=graduationApplyService.findByStateAndDistrict(0,district);
        }
        if(graduationApplyList==null||graduationApplyList.isEmpty()) return Result.getFail("找不到任何申请记录");
        return Result.getSuccess("成功",graduationApplyList);
    }

    @GetMapping("/api/graduationApply/toPrint")
    public Result findToPrint()throws Exception{
        List<GraduationApply> graduationApplyList=graduationApplyService.findByAllState(0,3);
        List<GraduationApply> printList=new ArrayList<GraduationApply>();
        if(graduationApplyList==null||graduationApplyList.isEmpty()) return Result.getFail("没有任何可打印的");
        for(GraduationApply graduationApply:graduationApplyList){
            if(graduationApply!=null&&graduationApply.getGraduate()!=null){
                printList.add(graduationApply);
            }
        }
        if(printList.isEmpty()) return Result.getFail("没有任何可打印的毕业证");
        return Result.getSuccess("成功",printList);
    }

    @ApiOperation(value = "毕业申请审核")
    @PutMapping("/api/graduationApply/{role}/{applyId}/{isPass}")
    public Result updateApply(@PathVariable("role") String role,@PathVariable("isPass") boolean isPass,@PathVariable("applyId") Long id) throws Exception {
        GraduationApply graduationApply=graduationApplyService.findById(id);

        if(graduationApply==null) {
            System.out.println("找不到申请");
            return Result.getFail("找不到申请");
        }

        if(!isPass){
            graduationApply.setApplyState(4);
            graduationApplyService.save(graduationApply);
            System.out.println("不通过");
            return Result.getSuccess("成功录入不通过审核");
        }
        else if(role.equals("区考试")&&graduationApply.getApplyState()==0){
            System.out.println("通过区县");
            graduationApply.setApplyState(1);
            graduationApplyService.save(graduationApply);
            return Result.getSuccess("成功录入区审核");
        }
        else if (role.equals("市考试")&&graduationApply.getApplyState()==1){
            System.out.println("通过市州");
            graduationApply.setApplyState(2);
            graduationApplyService.save(graduationApply);
            return Result.getSuccess("成功录入市审核");
        }
        else if(role.equals("省考试")&&graduationApply.getApplyState()==2){
            System.out.println("通过省考试");
            graduationApply.setApplyState(3);

            Graduate graduate=new Graduate();

            graduate.setCertified(true);
            graduate.setEducationLevel(graduationApply.getStudent().getEducation());
            graduate.setMajor(graduationApply.getMajor());
            graduate.setGraduateDate(graduationApply.getGraduateDate());
            graduate.setStudent(graduationApply.getStudent());
            graduate.setSchool(graduationApply.getStudent().getSchool());

            graduate=graduateService.save(graduate);

            if(graduate.getMajor().getId()==graduate.getStudent().getMajor().getId()){
                graduate.getStudent().setMajorGraduated(true);
                studentService.save(graduate.getStudent());
                WithHoldingGraduation withHoldingGraduation = withHoldingGraduationService.findByAdmissionId(graduate.getStudent().getAdmissionId());
                if(withHoldingGraduation!=null){
                    withHoldingGraduationService.delete(withHoldingGraduation.getId());
                }
            }

            graduationApply.setGraduate(graduate);
            graduationApply.setCertificateState(0);
            graduationApply=graduationApplyService.save(graduationApply);

            return Result.getSuccess("成功录入省审核，生成毕业证");
        }
        System.out.println("角色名字有误或者不属于你管");
        return Result.getFail("不属于你管辖");
    }

    @ApiOperation(value = "批量打印毕业证书")
    @PutMapping("/api/graduationApply/print")
    public Result print(@RequestBody Long[] id) throws Exception {
        GraduationApply graduationApply=null;
        int success=0;
        List<Long> nid=new ArrayList<Long>(Arrays.asList(id));
        for(Long gid:nid){
            graduationApply=graduationApplyService.findById(gid);
            if(graduationApply!=null){
                graduationApply.setCertificateState(1);
                graduationApplyService.save(graduationApply);
                success++;
            }
        }
        return Result.getSuccess("成功打印：",success);
    }

    @GetMapping("/api/graduationApply/toDistribute/{district}")
    public Result findToDistribute(@PathVariable("district") Long id)throws Exception{

        if(id==99) Result.getFail("你没有权限");

        List<GraduationApply> graduationApplyList=graduationApplyService.findToDistribute(1,id);
        if(graduationApplyList==null||graduationApplyList.isEmpty()) return  Result.getFail("找不到可以派发的");

        List<GraduationApply> distributeList=new ArrayList<GraduationApply>();

        for(GraduationApply graduationApply:graduationApplyList){
            if(graduationApply.getGraduate()!=null){
                distributeList.add(graduationApply);
            }
        }
        if(distributeList==null || distributeList.isEmpty()) return Result.getFail("找不到可以派发的");

        return Result.getSuccess("找到了",distributeList);
    }

    @PutMapping("/api/graduationApply/distribute")
    public Result distribute(@RequestBody DistributeParam info)throws Exception{
        GraduationApply graduationApply=null;
        for(Long id:info.getIdList()){
            graduationApply=graduationApplyService.findById(id);
            graduationApply.setReceiveStartDate(info.getReceiveStartDate());
            graduationApply.setReceiveEndDate(info.getReceiveEndDate());
            graduationApply.setReceivePosition(info.getReceivePosition());
            graduationApply.setCertificateState(2);
            graduationApplyService.save(graduationApply);
        }
        return Result.getSuccess("成功");
    }

    @GetMapping("/api/graduationApply/toConfirm/{district}")
    public Result findToConfirm(@PathVariable("district") Long id)throws Exception{

        if(id==99) Result.getFail("你没有权限");

        List<GraduationApply> graduationApplyList=graduationApplyService.findToDistribute(2,id);
        if(graduationApplyList==null||graduationApplyList.isEmpty()) return  Result.getFail("找不到可以派发的");

        List<GraduationApply> distributeList=new ArrayList<GraduationApply>();

        for(GraduationApply graduationApply:graduationApplyList){
            if(graduationApply.getGraduate()!=null){
                distributeList.add(graduationApply);
            }
        }
        if(distributeList==null || distributeList.isEmpty()) return Result.getFail("找不到可以派发的");

        return Result.getSuccess("找到了",distributeList);
    }

    @PutMapping("/api/graduationApply/confirmAll")
    public Result confirmAll(@RequestBody DistributeParam info)throws Exception{
        GraduationApply graduationApply=null;
        for(Long id:info.getIdList()){
            graduationApply=graduationApplyService.findById(id);
            graduationApply.setCertificateState(3);
            graduationApplyService.save(graduationApply);
        }
        return Result.getSuccess("成功");
    }

    @PutMapping("/api/graduationApply/confirm/{id}")
    public Result confirm(@PathVariable("id") Long id)throws Exception{
        GraduationApply graduationApply=null;
            graduationApply=graduationApplyService.findById(id);
            graduationApply.setCertificateState(3);
            graduationApplyService.save(graduationApply);
        return Result.getSuccess("成功");
    }
}
