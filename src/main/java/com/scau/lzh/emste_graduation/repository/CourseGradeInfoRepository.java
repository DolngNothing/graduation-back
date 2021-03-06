package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.CourseGradeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseGradeInfoRepository extends JpaRepository<CourseGradeInfo,Long> {
    List<CourseGradeInfo> findAllByStudent_AdmissionId(String admissionId);
}
