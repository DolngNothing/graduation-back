package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.CEgradeInfo;
import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.repository.CEgradeInfoRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CEgradeInfoService {
    @Autowired
    private CEgradeInfoRepository cEgradeInfoRepository;

    @Autowired
    private StudentService studentService;

    public CEgradeInfo save(CEgradeInfo cEgradeInfo) throws Exception{
        try {
            cEgradeInfo=cEgradeInfoRepository.save(cEgradeInfo);
        }catch (Exception e){
            System.out.println("****中英文表保存失败******");
        }
        return cEgradeInfo;
    }

    public List<CEgradeInfo> cEgradeInfoList() throws Exception{
        List<CEgradeInfo> cEgradeInfoList=null;
        try {
            cEgradeInfoList=cEgradeInfoRepository.findAll();
        }catch (Exception e){
            System.out.println("****中英文表返回列表失败********");
        }
        return cEgradeInfoList;
    }

    public CEgradeInfo findByAdmissionId(String admissionId) throws Exception{
        Student student=studentService.findById(admissionId);
        if(student==null){
            return null;
        }
        return student.getcEgradeInfo();
    }

    public CEgradeInfo findById(Long id) throws Exception{
        CEgradeInfo cEgradeInfo=null;
        try {
            cEgradeInfo=cEgradeInfoRepository.findById(id).orElse(null);
        }catch (Exception e){
            System.out.println("*******id寻找中英文表失败********");
        }
        return cEgradeInfo;
    }

    public void deleteById(Long id){
        try {
            cEgradeInfoRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("*******id删除中英文表失败********");
        }
    }
}
