package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.FrontDegree;
import com.scau.lzh.emste_graduation.repository.FrontDegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontDegreeService {
    @Autowired
    private FrontDegreeRepository frontDegreeRepository;

    public FrontDegree save(FrontDegree frontDegree)throws Exception{
        try {
            return frontDegreeRepository.saveAndFlush(frontDegree);
        } catch (Exception e) {
            System.out.println("******前置学历保存失败******");
        }
        return null;
    }

    public List<FrontDegree> findByAdmissionId(String admissionId)throws Exception{
        try {
            return frontDegreeRepository.findAllByStudent_AdmissionId(admissionId);
        } catch (Exception e) {
            System.out.println("******AdmissionId查找前置学历失败******");
        }
        return null;
    }

    public List<FrontDegree> findByAdmissionIdAndState(String admissionId,int state)throws Exception{
        try {
            return frontDegreeRepository.findAllByStudent_AdmissionIdAndState(admissionId,state);
        } catch (Exception e) {
            System.out.println("******AdmissionId查找前置学历失败******");
        }
        return null;
    }

    public List<FrontDegree> findAll()throws Exception{
        try {
            return frontDegreeRepository.findAll();
        } catch (Exception e) {
            System.out.println("******查找全部前置学历失败******");
        }
        return  null;
    }

    public FrontDegree findById(Long id)throws Exception{
        try {
            return frontDegreeRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("******Id查找前置学历失败******");
        }
        return null;
    }

    public FrontDegree findByGraduateId(Long id)throws Exception{
        try {
            return frontDegreeRepository.findByGraduate_Id(id);
        } catch (Exception e) {
            System.out.println("******GraduateId查找前置学历失败******");
        }
        return null;
    }

}
