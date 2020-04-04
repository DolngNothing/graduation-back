package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.*;
import com.scau.lzh.emste_graduation.service.*;
import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WithHoldingGraduationController {


    @Autowired
    private WithHoldingGraduationService withHoldingGraduationService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private CourseGradeInfoService courseGradeInfoService;

    @Autowired
    private GraduateService graduateService;

    @Autowired
    private ThesisService thesisService;

    @PostMapping("/api/withHoldingGraduation")
    public Result createWithHoldingGraduationList() throws Exception {
        List<WithHoldingGraduation> withHoldingGraduationList=new ArrayList<WithHoldingGraduation>();
        List<Student> studentList = studentService.findByMajorGraduated();
        StringBuffer result=new StringBuffer();
        System.out.println("#######"+studentList);
        withHoldingGraduationList=withHoldingGraduationService.withHoldingGraduationList();
        WithHoldingGraduation withHoldingGraduation=null;
        for(Student student:studentList) {
            if (student.isMajorGraduated() == false) {
                if (isAbleToGraduate(student, student.getMajor(), graduateService, majorService, courseGradeInfoService, thesisService,result)) {
                    withHoldingGraduation = new WithHoldingGraduation();
                    withHoldingGraduation.setStudent(student);
                    withHoldingGraduation.setWithHoldingDate(new Date());
                    withHoldingGraduationService.save(withHoldingGraduation);
                    withHoldingGraduationList.add(withHoldingGraduation);
                }
            }
            result= new StringBuffer();
        }
        if(withHoldingGraduationList.isEmpty()) return Result.getFail("没有任何一个学生有预提毕业资格");
        return Result.getSuccess("成功预提",withHoldingGraduationList);
    }

    @GetMapping("/api/withHoldingGraduation/{page}/{size}")
    public Result getList(@PathVariable("page") Integer page,@PathVariable("size") Integer size) throws Exception{
        Page<WithHoldingGraduation> byPage = withHoldingGraduationService.findByPage(page, size);
        if(byPage==null||byPage.isEmpty()){
            return Result.getFail("没有找到预提");
        }
        else {
            return Result.getSuccess("找到预提",byPage);
        }
    }


    @GetMapping("api/withHolding/{admissionId}")
    public Result getById(@PathVariable("admissionId") String id) throws Exception{
        WithHoldingGraduation withHoldingGraduation=withHoldingGraduationService.findByAdmissionId(id);
        if(withHoldingGraduation == null) return Result.getFail("找不到该考生的预提信息");
        return Result.getSuccess("查询成功",withHoldingGraduation);
    }


    /**
     * 目前可以预提毕业必须是学生当前专业还未毕业，且修够了该专业的课程要求{全部科目大于60分}
     * @param student
     * @return
     * @throws Exception
     */
    public  boolean isAbleToGraduate(Student student,Major major,
                                     GraduateService graduateService,MajorService majorService,
                                     CourseGradeInfoService courseGradeInfoService,ThesisService thesisService,StringBuffer result) throws Exception {
            if(major==null||student==null){
                System.out.println("*******学生或专业为空********");
                result.append("学生或专业为空"+"\n");
                return false;
            }

        /**
         * 检测：不能有重复专业毕业，也不可以同时申请毕业
         * 本科毕业必须要有专科毕业证
         */
        if(student.getEducation()==null){
            System.out.println("*******学历为空********");
            result.append("学历为空"+"\n");
            return false;
        }

        if(student.getEducation().equals("本科")){
            System.out.println("testtesttesttesttesttest");

            List<Graduate> graduateList = graduateService.findByAdmissionId(student.getAdmissionId());

            System.out.println(graduateList);

            if(graduateList==null||graduateList.isEmpty()){
                System.out.println("*******本科毕业必须要有前置学历********");
                result.append("本科毕业必须要有前置学历"+"\n");
                return false;
            }

            boolean graduateTrue=false;

            for(Graduate graduate:graduateList){
                if(graduate.isCertified()){
                    graduateTrue=true;
                    break;
                }
            }
            if(!graduateTrue) {
                System.out.println("*******本科毕业必须要有被承认的前置学历********");
                result.append("本科毕业必须要有被承认的前置学历"+"\n");
                return false;
            }
        }


        List<Course> courseList=majorService.findById(major.getId()).getCourseList();

            List<CourseGradeInfo> courseGradeInfoList= courseGradeInfoService.findByAdmissionId(student.getAdmissionId());

            if(courseGradeInfoList==null){
                System.out.println("*******学生没有成绩表********");
                result.append("学生没有成绩表"+"\n");
                return false;
            }

            if(student.getcEgradeInfo().getChineseGrade()<60||student.getcEgradeInfo().getEnglishGrade()<60){
                System.out.println("*******中英文成绩不过关********");
                result.append("中英文成绩不过关"+"\n");
                return false;
            }

            List<Thesis> thesisList=thesisService.findAllByAdmissionId(student.getAdmissionId());

            boolean hasThesis=false;

            for(Thesis thesis:thesisList){
                if(thesis.getMajor().getId()==major.getId()&&thesis.getState()==1){
                    hasThesis=true;
                    break;
                }
            }
            if(!hasThesis){
                System.out.println("*******没有专业论文或者论文不过关********");
                result.append("没有专业论文或者论文不过关"+"\n");
                return false;
            }

            for(CourseGradeInfo courseGradeInfo:courseGradeInfoList){
                if(courseList.contains(courseGradeInfo.getCourse())){
                    if(courseGradeInfo.getScore()>=60)
                    continue;
                    else {
                        System.out.println("*******没有修够课程********");
                        result.append("没有修够课程"+"\n");
                        return false;
                    }
                }
            }
            return true;
        }

}
