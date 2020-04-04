package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.Status;
import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.service.StatusService;
import com.scau.lzh.emste_graduation.service.StudentService;
import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;
    @Autowired
    private StudentService studentService;

    /**
     * 这里只保存了student实例，但cascade会导致status也被更新
     * @param admissionId
     * @param status
     * @return
     * @throws Exception
     */
    @PostMapping("/api/status/{admissionId}")
    public Status save(@PathVariable("admissionId") String admissionId,  @RequestBody Status status) throws Exception {
        Student student=studentService.findById(admissionId);
        if(student!=null){
            student.setStatus(status);
            studentService.save(student);
        }
        return status;
    }

    @GetMapping("/api/status/{admissionId}/{role}/{city}/{dis}")
    public Result findByAdmissionId(@PathVariable("admissionId") String admissionId ,@PathVariable("role") String role,@PathVariable(value = "city",required = false) Long city,@PathVariable(value = "dis",required = false) Long dis) throws Exception {
        Student student=studentService.findById(admissionId);
        if(student==null) return Result.getFail("不存在该学生");
        if(student.getStatus()==null) return Result.getFail("找不到学生学籍");
        if(role.equals("用户")) return Result.getSuccess("成功",student);

        if(city!=99&&dis!=99&&(city!=student.getUser().getCity().getId()||dis!=student.getUser().getDistrict().getId())) return Result.getFail("区考试只能打印该区的学生");

        if(city!=99&&city!=student.getUser().getCity().getId()) return Result.getFail("市考试只能打印本市学生");

        return Result.getSuccess("成功",student);

    }

}
