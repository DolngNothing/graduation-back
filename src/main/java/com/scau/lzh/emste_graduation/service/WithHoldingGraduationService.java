package com.scau.lzh.emste_graduation.service;
import com.scau.lzh.emste_graduation.domain.WithHoldingGraduation;
import com.scau.lzh.emste_graduation.repository.WithHoldingGraduationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WithHoldingGraduationService {
    @Autowired
    private WithHoldingGraduationRepository withHoldingRepository;

    public List<WithHoldingGraduation> withHoldingGraduationList() throws Exception{
        List<WithHoldingGraduation> withHoldingGraduationList=withHoldingRepository.findAll();
        try {
            return withHoldingGraduationList;
        } catch (Exception e) {
            System.out.println("********返回预提表出错********");
        }
        return null;
    }

    public void save(WithHoldingGraduation whg) throws Exception{
        try {
            whg=withHoldingRepository.saveAndFlush(whg);
        } catch (Exception e) {
            System.out.println("********保存预提表出错********");
        }
    }

    public Page<WithHoldingGraduation> findByPage(Integer pageNum,Integer size) throws  Exception{
        try {
            PageRequest pageRequest = PageRequest.of(pageNum - 1, size);
            Page<WithHoldingGraduation> withHoldingGraduationPage= withHoldingRepository.findAll(pageRequest);
            return withHoldingGraduationPage;
        } catch (Exception e) {
            System.out.println("********分页查询预提出错********");
        }
        return null;
    }

    public WithHoldingGraduation findByAdmissionId(String id)throws  Exception{
        try {
            return withHoldingRepository.findByStudent_AdmissionId(id);
        } catch (Exception e) {
            System.out.println("********admissionID查找预提表出错********");
        }
        return null;
    }

    public void delete(Long id)throws Exception{
        try {
            withHoldingRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("********删除预提表出错********");
        }
    }
}
