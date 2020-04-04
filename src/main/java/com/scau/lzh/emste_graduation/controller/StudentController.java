package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.service.StudentService;

import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.crypto.RsaMd5CksumType;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/api/student")
    public void save(@RequestBody Student student) throws Exception {
        studentService.save(student);
    }

    @GetMapping("/api/student/{admissionId}")
    public Result findById(@PathVariable("admissionId") String admissionId) throws Exception{
        Student student=studentService.findById(admissionId);
        if(student==null){
            return Result.getFail("没有该学生");
        }
        return Result.getSuccess("成功查到学生",student);
    }

    /**
     * 登录的时候用user的账号id查到student实体
     * @param account
     * @return
     * @throws Exception
     */
    @GetMapping("/api/student/user/{account}")
    public Result findByUser(@PathVariable("account") String account) throws Exception{
        Student student=studentService.findByUser(account);
        if(student==null) return Result.getFail("找不到该学生");
        return Result.getSuccess("成功找到该学生",student);
    }

}
