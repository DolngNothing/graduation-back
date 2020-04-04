package com.scau.lzh.emste_graduation.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scau.lzh.emste_graduation.domain.*;
import com.scau.lzh.emste_graduation.repository.GraduateRepository;
import com.scau.lzh.emste_graduation.utils.GraduateParam;
import com.scau.lzh.emste_graduation.utils.ThesisParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
public class GraduateService {
    @Autowired
    private GraduateRepository graduateRepository;

    public Graduate save(Graduate graduate) throws Exception{
        try {
            graduate=graduateRepository.saveAndFlush(graduate);
        }catch (Exception e){
            System.out.println("******毕业生保存失败******");
        }
        return graduate;
    }

    public List<Graduate> findByYear(Integer year)throws  Exception{
        List<Graduate> graduateList=null;
        try {
            graduateList= graduateRepository.findAllByGraduateDate(year);
        }catch (Exception e){
            System.out.println("******毕业生查找失败******");
        }
        return graduateList;
    }

    public List<Graduate> findByAdmissionId(String admissionId)throws Exception{
        try {
            return graduateRepository.findAllByStudent_AdmissionId(admissionId);
        }catch (Exception e){
            System.out.println("******学籍号毕业生查找失败******");
        }
        return null;
    }

    public Graduate findById(Long id)throws Exception{

        try {
            return graduateRepository.findById(id).orElse(null);
        }catch (Exception e){
            System.out.println("******id毕业生查找失败******");
        }
        return null;
    }

    public void delete(Graduate graduate)throws Exception{
        try {
            graduateRepository.delete(graduate);
        } catch (Exception e) {
            System.out.println("******删除失败******");
        }
    }


    public List<Graduate> queryGraduate(final GraduateParam graduateParam){
        Specification<Graduate> specification=new Specification<Graduate>() {
            @Override
            public Predicate toPredicate(Root<Graduate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(graduateParam.getCity()!=null){
                    predicates.add(criteriaBuilder.equal(root.<Student>get("student").<User>get("user").<String>get("city"),graduateParam.getCity()));
                }

                if(graduateParam.getDistrict()!=null){
                    predicates.add(criteriaBuilder.equal(root.<Student>get("student").<User>get("user").<String>get("district"),graduateParam.getDistrict()));
                }

                if(graduateParam.getMajorId()!=null&&!(graduateParam.getMajorId()==0)){
                    predicates.add(criteriaBuilder.equal(root.<Major>get("major").<Long>get("id"),graduateParam.getMajorId()));
                }

                if(graduateParam.getDistrict()!=null){
                    predicates.add(criteriaBuilder.equal(root.<Student>get("student").<User>get("user").<String>get("district"),graduateParam.getDistrict()));
                }

                if(graduateParam.getGraduateDate()!=null&&graduateParam.getGraduateYear()==null){
                    predicates.add(criteriaBuilder.between(root.<Date>get("graduateDate"),graduateParam.getOneStartTime(graduateParam.getGraduateDate()),graduateParam.getOneEndTime(graduateParam.getGraduateDate())));
                }

                else if(graduateParam.getGraduateDate()==null&&graduateParam.getGraduateYear()!=null){
                    predicates.add(criteriaBuilder.between(root.<Date>get("graduateDate"),graduateParam.getYearStartTime(graduateParam.getGraduateDate()),graduateParam.getYearEndTime(graduateParam.getGraduateDate())));
                }

                predicates.add(criteriaBuilder.equal(root.<Boolean>get("certified"),true));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        List<Graduate> graduateList=graduateRepository.findAll(specification);
        return graduateList;
    }

}



