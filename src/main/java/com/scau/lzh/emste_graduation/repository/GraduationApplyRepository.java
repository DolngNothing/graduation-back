package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.GraduationApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraduationApplyRepository extends JpaRepository<GraduationApply,Long> {
    List<GraduationApply> findAllByStudent_AdmissionIdAndAndApplyState(String admissionId, int applyState);
    List<GraduationApply> findAllByStudent_AdmissionId(String admissionId);
    List<GraduationApply> findAllByCertificateState(int state);
    List<GraduationApply> findAllByApplyState(int state);
    List<GraduationApply> findAllByApplyStateAndStudent_User_City_Id(int state,Long city);
    List<GraduationApply> findAllByApplyStateAndStudent_User_District_Id(int state,Long district);
    List<GraduationApply> findAllByCertificateStateAndApplyState(Integer cerState,int applyState);
    List<GraduationApply> findAllByCertificateStateAndStudent_User_District_Id(Integer state,Long district);
    GraduationApply findByGraduate_Id(Long id);
}
