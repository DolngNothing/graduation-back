package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.CEgradeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CEgradeInfoRepository extends JpaRepository<CEgradeInfo,Long> {
}