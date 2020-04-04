package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.PostRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRegisterRepository extends JpaRepository<PostRegister,Long> {
    PostRegister findByStudent_AdmissionId(String admissionId);
}
