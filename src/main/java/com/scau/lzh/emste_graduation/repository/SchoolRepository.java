package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long> {
}
