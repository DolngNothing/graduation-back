package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.Status;
import com.scau.lzh.emste_graduation.repository.StatusRepository;
import com.scau.lzh.emste_graduation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private StudentService studentService;

    public Status save(Status status) throws  Exception{
        try {
            status=statusRepository.save(status);
        }catch (Exception e){

        }
        return status;
    }

    /**
     * 利用准考证找到学籍
     * @param admissionId
     * @return
     */
    public Status findByAdmissionId(String admissionId){
        return null;
    }

    public List<Status> statusList(){
        return statusRepository.findAll();
    }

}
