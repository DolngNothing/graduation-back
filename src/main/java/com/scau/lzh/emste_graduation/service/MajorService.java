package com.scau.lzh.emste_graduation.service;
import com.scau.lzh.emste_graduation.domain.Major;
import com.scau.lzh.emste_graduation.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    private MajorRepository majorRepository;

    public List<Major> majorList() throws Exception{
        List<Major> majorList=majorRepository.findAll();
        return majorList;
    }

    public Major save(Major major) throws Exception{
        try {
            major=majorRepository.saveAndFlush(major);
        }catch (Exception e){
            System.out.println("********保存专业出错********");
        }
        return major;
    }

    public Major findByName(){
        return null;
    }

    public Major findById(Long id) throws Exception{
        Major major=null;
        try {
            major=majorRepository.findById(id).orElse(null);
        }catch (Exception e){
            System.out.println("********id找寻专业出错********");
        }
        return major;
    }

}
