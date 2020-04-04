package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.GraduateCertify;
import com.scau.lzh.emste_graduation.repository.GraduateCertifyRepository;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduateCertifyService {
    @Autowired
    private GraduateCertifyRepository graduateCertifyRepository;

    public GraduateCertify save(GraduateCertify graduateCertify)throws Exception{
        try {
            return graduateCertifyRepository.saveAndFlush(graduateCertify);
        } catch (Exception e) {
            System.out.println("保存毕业证明失败");
        }
        return null;
    }

    public List<GraduateCertify> findByAdmissionId(String admissionId)throws Exception{
        try {
            return graduateCertifyRepository.findAllByStudent_AdmissionId(admissionId);
        } catch (Exception e) {
            System.out.println("查找证明失败");
        }
        return null;
    }

    public GraduateCertify findById(Long id)throws Exception{
        try {
            return graduateCertifyRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("查找证明失败");
        }
        return null;
    }
    public List<GraduateCertify> findByAccount(String account)throws Exception{
        try {
            return graduateCertifyRepository.findAllByUser_Account(account);
        } catch (Exception e) {
            System.out.println("Account查找证明失败");
        }
        return null;
    }

    public GraduateCertify findByGraduateId(Long id)throws Exception{
        try {
            return graduateCertifyRepository.findByGraduate_Id(id);
        } catch (Exception e) {
            System.out.println("GraduateId查找证明失败");
        }
        return null;
    }
}
