package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.PostRegister;
import com.scau.lzh.emste_graduation.service.PostRegisterService;
import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostRegisterController {
    @Autowired
    PostRegisterService postRegisterService;

    @GetMapping("api/postRegister/{admissionId}")
    public Result getPost(@PathVariable("admissionId") String admissionId) throws Exception{
        PostRegister postRegister = postRegisterService.findByAdmissionId(admissionId);
        if(postRegister==null) return Result.getFail("找不到研究生报考");
        return Result.getSuccess("成功",postRegister);
    }
}
