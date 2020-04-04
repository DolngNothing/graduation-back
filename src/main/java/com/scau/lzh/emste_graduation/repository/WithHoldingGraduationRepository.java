package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.WithHoldingGraduation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithHoldingGraduationRepository extends JpaRepository<WithHoldingGraduation,Long> {
    WithHoldingGraduation findByStudent_AdmissionId(String id);
}
