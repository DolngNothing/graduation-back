package com.scau.lzh.emste_graduation.repository;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.scau.lzh.emste_graduation.domain.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GraduateRepository extends JpaRepository<Graduate,Long>, JpaSpecificationExecutor {

    @Query("select g from Graduate g where year(g.graduateDate) = ?1 and g.certified = true")
    List<Graduate> findAllByGraduateDate(int year);

    List<Graduate> findAllByStudent_AdmissionId(String admissionId);

    List<Graduate> findAllByStudent_AdmissionIdAndCertifiedTrue(String admissionId);

}


