package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.domain.User;
import com.scau.lzh.emste_graduation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> studentList() throws Exception{
        return studentRepository.findAll();
    }

    public Student findById(String admissionId) throws Exception{
        try {
            Student student=studentRepository.findById(admissionId).orElse(null);
            return student;
        }catch (Exception e ){
            System.out.println("*****找学生失败*****");
        }
        return null;
    }

    public Student save(Student student) throws Exception{
        try {
            student=studentRepository.saveAndFlush(student);
        }catch (Exception e){
            System.out.println("******保存学生失败*******");
        }
        return student;
    }

    public Student findByUser(String account) throws Exception{
        Student student=null;
        try {
            student=studentRepository.findByUser_Account(account);
        }catch(Exception e){
            System.out.println("*****用user学生失败*******");
        }
        return student;
    }

    public List<Student> findByMajorGraduated() throws Exception{
        return studentRepository.findAllByIsMajorGraduatedFalse();
    }
}
