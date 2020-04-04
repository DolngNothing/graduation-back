package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.FrontDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrontDegreeRepository extends JpaRepository<FrontDegree,Long> {
    List<FrontDegree> findAllByGraduate_Student_AdmissionId(String admissionId);
    List<FrontDegree> findAllByStudent_AdmissionIdAndState(String admissionId,int state);
    FrontDegree findByGraduate_Id(Long id);

    List<FrontDegree> findAllByStudent_AdmissionId(String admissionId);
}
