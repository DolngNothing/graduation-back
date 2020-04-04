package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.Graduate;
import com.scau.lzh.emste_graduation.domain.GraduateCertify;
import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.domain.User;
import com.scau.lzh.emste_graduation.service.GraduateCertifyService;
import com.scau.lzh.emste_graduation.service.GraduateService;
import com.scau.lzh.emste_graduation.service.UserService;
import com.scau.lzh.emste_graduation.utils.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class GraduateCertifyController {
    @Autowired
    private GraduateService graduateService;

    @Autowired
    private GraduateCertifyService graduateCertifyService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/graduateCertify/{graduateId}/{type}/{account}")
    public Result save(@PathVariable("graduateId")Long id,@PathVariable("type") Integer type,@PathVariable("account") String account) throws Exception {
        Graduate graduate=graduateService.findById(id);
        if(graduate==null||graduate.getStudent()==null){
            System.out.println("不存在该毕业生或证书");
            return Result.getFail("不存在该毕业生或证书");
        }

        if(graduateCertifyService.findByGraduateId(id)!=null) return Result.getFail("该毕业证明重复提交");

        User user= userService.findByAccount(account);

        if(user ==null) return Result.getFail("你到底是谁");

        GraduateCertify graduateCertify=new GraduateCertify();
        graduateCertify.setApplyDate(new Date());
        graduateCertify.setCertifyType(type);
        graduateCertify.setUser(user);
        graduateCertify.setGraduate(graduate);
        graduateCertify.setStudent(graduate.getStudent());

        graduateCertify=graduateCertifyService.save(graduateCertify);
        return Result.getSuccess("成功打印",graduateCertify);
    }

    @GetMapping("/api/graduateCertify/admissionId/{id}")
    public Result findByAdmissionId(@PathVariable("id") String id) throws Exception {
        List<GraduateCertify> graduateCertifyList=graduateCertifyService.findByAdmissionId(id);
        if(graduateCertifyList==null || graduateCertifyList.isEmpty()) return Result.getFail("找不到毕业证书证明");
        return Result.getSuccess("找到了",graduateCertifyList);
    }

    @GetMapping("/api/graduateCertify/id/{id}")
    public Object findById(@PathVariable("id") Long id) throws Exception {
        GraduateCertify graduateCertify=graduateCertifyService.findById(id);
        return graduateCertify;
    }

    @GetMapping("/api/graduateCertify/account/{account}")
    public Result findById(@PathVariable("account") String account) throws Exception {
        List<GraduateCertify> graduateCertifyList = graduateCertifyService.findByAccount(account);
        if(graduateCertifyList==null ||graduateCertifyList.isEmpty()) return Result.getFail("找不到你负责的证明");
        return Result.getSuccess("找到",graduateCertifyList);
    }

}
