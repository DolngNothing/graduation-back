package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.GraduateCertify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraduateCertifyRepository extends JpaRepository<GraduateCertify,Long> {
    List<GraduateCertify>  findAllByStudent_AdmissionId(String admissionId);
    List<GraduateCertify> findAllByUser_Account(String account);
    GraduateCertify findByGraduate_Id(Long id);
}
