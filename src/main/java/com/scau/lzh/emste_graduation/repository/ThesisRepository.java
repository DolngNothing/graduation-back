package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.Major;
import com.scau.lzh.emste_graduation.domain.School;
import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.domain.Thesis;
import com.scau.lzh.emste_graduation.utils.ThesisParam;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis,Long> , JpaSpecificationExecutor {
    List<Thesis> findAllByStudent_AdmissionId(String admissionId);
    List<Thesis> findAllByStudent_User_City(String city);
    List<Thesis> findAllByStudent_User_District(String district);

    @Query(value = "select t from Thesis t " +
            " left join Student s on t.student.admissionId=s.admissionId " +
            "left join User u on u.account= s.user.account " +
            " where year(t.passDate) = :y and u.city = :city "  )
    List<Thesis> findAllByCity(@Param("city")String city, @Param("y")int year);

    List<Thesis> findAllByState(int state);
}
