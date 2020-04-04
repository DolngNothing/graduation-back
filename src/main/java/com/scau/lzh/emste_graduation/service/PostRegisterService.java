package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.PostRegister;
import com.scau.lzh.emste_graduation.repository.PostRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostRegisterService {
    @Autowired
    private PostRegisterRepository postRegisterRepository;

    public PostRegister save(PostRegister postRegister) throws Exception{
        try {
            return postRegisterRepository.save(postRegister);
        } catch (Exception e) {
            System.out.println("********保存研究生出错********");
        }
        return null;
    }

    public PostRegister findByAdmissionId(String admissionId)throws Exception{
        try {
            return postRegisterRepository.findByStudent_AdmissionId(admissionId);
        } catch (Exception e) {
            System.out.println("********查找研究生出错********");
        }
        return null;
    }
}
