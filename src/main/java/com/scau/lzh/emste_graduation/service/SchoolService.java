package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.School;
import com.scau.lzh.emste_graduation.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    public School getSchool(Long id)throws Exception{
        try {
            return schoolRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("学校出错");
        }
        return null;
    }

    public List<School> getSchools()throws Exception{
        try {
            return schoolRepository.findAll();
        } catch (Exception e) {
            System.out.println("学校出错");
        }
        return null;
    }
}
