package com.scau.lzh.emste_graduation.repository;


import com.scau.lzh.emste_graduation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByUser_Account(String account);
    List<Student> findAllByIsMajorGraduatedFalse();
}
