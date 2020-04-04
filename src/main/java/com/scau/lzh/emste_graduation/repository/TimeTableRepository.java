package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable,Long> {
}
