package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
    List<District> findAllByCity_Id(Long id);
}
