package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.GraduationApply;
import com.scau.lzh.emste_graduation.repository.GraduationApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduationApplyService {
    @Autowired
    private GraduationApplyRepository graduationApplyRepository;

    public GraduationApply save(GraduationApply graduationApply)throws Exception{
        try {
            return graduationApplyRepository.saveAndFlush(graduationApply);
        } catch (Exception e) {
            System.out.println("******保存毕业申请处理表失败******");
        }
        return null;
    }

    public List<GraduationApply> findByAdmissIdAndApplyState(String admissionId, int applyState)throws  Exception{
        try {
            return graduationApplyRepository.findAllByStudent_AdmissionIdAndAndApplyState(admissionId,applyState);
        } catch (Exception e) {
            System.out.println("******findByAdmissIdAndApplyState失败******");
        }
        return null;
    }

    public List<GraduationApply> findByAdmissId(String admissionId)throws  Exception{
        try {
            return graduationApplyRepository.findAllByStudent_AdmissionId(admissionId);
        } catch (Exception e) {
            System.out.println("******findByAdmissId失败******");
        }
        return null;
    }

    public GraduationApply findById(Long id)throws  Exception{
        try {
            return graduationApplyRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("******findById失败******");
        }
        return null;
    }

    public List<GraduationApply> findByCertificateState(int state)throws  Exception{
        try {
            return graduationApplyRepository.findAllByCertificateState(state);
        } catch (Exception e) {
            System.out.println("******findAllByCertificateState失败******");
        }
        return null;
    }

    public GraduationApply findByGraduateId(Long id)throws  Exception{
        try {
            return graduationApplyRepository.findByGraduate_Id(id);
        } catch (Exception e) {
            System.out.println("******findBygraduate失败******");
        }
        return null;
    }

    public List<GraduationApply> findByState(int state)throws Exception{
        try {
            return graduationApplyRepository.findAllByApplyState(state);
        } catch (Exception e) {
            System.out.println("******findByState失败******");
        }
        return null;
    }

    public List<GraduationApply> findByStateAndCity(int state,Long city)throws Exception{
        try {
            return graduationApplyRepository.findAllByApplyStateAndStudent_User_City_Id(state,city);
        } catch (Exception e) {
            System.out.println("******findByStateAndCity失败******");
        }
        return null;
    }

    public List<GraduationApply> findByStateAndDistrict(int state,Long district)throws Exception{
        try {
            return graduationApplyRepository.findAllByApplyStateAndStudent_User_District_Id(state,district);
        } catch (Exception e) {
            System.out.println("******findByStateAndCity失败******");
        }
        return null;
    }

    public List<GraduationApply> findByAllState(Integer cstate,int astate)throws Exception{
        try {
            return graduationApplyRepository.findAllByCertificateStateAndApplyState(cstate,astate);
        } catch (Exception e) {
            System.out.println("******findByAllState失败******");
        }
        return null;
    }

    public List<GraduationApply> findToDistribute(Integer state,Long district)throws Exception{
        try {
            return graduationApplyRepository.findAllByCertificateStateAndStudent_User_District_Id(state,district);
        } catch (Exception e) {
            System.out.println("******findToDistrict失败******");
        }
        return null;
    }
}
