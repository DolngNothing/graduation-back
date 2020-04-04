package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    List<City> findAllByIdNot(Long id);
}
