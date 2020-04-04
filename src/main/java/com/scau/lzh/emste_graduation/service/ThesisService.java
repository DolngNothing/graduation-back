package com.scau.lzh.emste_graduation.service;


import com.scau.lzh.emste_graduation.domain.Major;
import com.scau.lzh.emste_graduation.domain.School;
import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.domain.Thesis;
import com.scau.lzh.emste_graduation.repository.ThesisRepository;
import com.scau.lzh.emste_graduation.utils.ThesisParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ThesisService {
    @Autowired
    private ThesisRepository thesisRepository;

    public Thesis save(Thesis thesis) throws Exception{
        try {
            return thesisRepository.save(thesis);
        } catch (Exception e) {
            System.out.println("*****保存论文失败*******");
        }
        return null;
    }

    public List<Thesis> findOneYearByCity(String dis,int year) throws Exception{
        try {
            return thesisRepository.findAllByCity(dis,year);
        } catch (Exception e) {
            System.out.println("*****查找论文失败*******");
        }
        return null;
    }

    public List<Thesis> findAllByAdmissionId(String admissionId)throws Exception{
        try {
            return thesisRepository.findAllByStudent_AdmissionId(admissionId);
        } catch (Exception e) {
            System.out.println("*****id查找论文失败*******");
        }
        return null;
    }


    public List<Thesis> queryTheses(final ThesisParam thesisParam){
        Specification<Thesis> specification=new Specification<Thesis>() {
            @Override
            public Predicate toPredicate(Root<Thesis> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(thesisParam.getMajor()!=null){
                    predicates.add(criteriaBuilder.equal(root.<Major>get("major").<Long>get("id"),thesisParam.getMajor().getId()));
                }
                if(thesisParam.getSchool()!=null){
                    predicates.add(criteriaBuilder.equal(root.<Student>get("student").<School>get("school").<String>get("id"),thesisParam.getSchool().getId()));
                }
                predicates.add(criteriaBuilder.equal(root.get("state"),(int)1));
                if(thesisParam.getDate()!=null){
                    predicates.add(criteriaBuilder.between(root.<Date>get("passDate"),thesisParam.setStartTime(thesisParam.getDate()),thesisParam.setEndTime(thesisParam.getDate())));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<Thesis> thesisList=thesisRepository.findAll(specification);
        return thesisList;
    }

    public List<Thesis> findAll()throws Exception{
        try {
            return thesisRepository.findAllByState(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
