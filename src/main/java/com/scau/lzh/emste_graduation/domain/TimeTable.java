package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "timeTable")
@ApiModel("时间表")
public class TimeTable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "毕业预提开始时间")
    @Column(name = "withHoldingGraduationStartDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    private Date withHoldingGraduationStartDate;

    @Column(name = "withHoldingGraduationEndDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    private Date withHoldingGraduationEndDate;

    @ApiModelProperty(value = "前置学历申请开始时间")
    @Column(name = "frontDegreeApplyStartDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    private Date frontDegreeApplyStartDate;

    @Column(name = "frontDegreeApplyEndDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    private Date frontDegreeApplyEndDate;


    @ApiModelProperty(value = "毕业申请开始时间")
    @Column(name = "graduateApplyStartDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    private Date graduateApplyStartDate;

    @Column(name = "graduateApplyEndDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    private Date graduateApplyEndDate;

    @ApiModelProperty(value = "毕业申请审核开始时间")
    @Column(name = "graduateCheckStartDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    private Date graduateCheckStartDate;


    public TimeTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getWithHoldingGraduationStartDate() {
        return withHoldingGraduationStartDate;
    }

    public void setWithHoldingGraduationStartDate(Date withHoldingGraduationStartDate) {
        this.withHoldingGraduationStartDate = withHoldingGraduationStartDate;
    }

    public Date getWithHoldingGraduationEndDate() {
        return withHoldingGraduationEndDate;
    }

    public void setWithHoldingGraduationEndDate(Date withHoldingGraduationEndDate) {
        this.withHoldingGraduationEndDate = withHoldingGraduationEndDate;
    }

    public Date getFrontDegreeApplyStartDate() {
        return frontDegreeApplyStartDate;
    }

    public void setFrontDegreeApplyStartDate(Date frontDegreeApplyStartDate) {
        this.frontDegreeApplyStartDate = frontDegreeApplyStartDate;
    }

    public Date getFrontDegreeApplyEndDate() {
        return frontDegreeApplyEndDate;
    }

    public void setFrontDegreeApplyEndDate(Date frontDegreeApplyEndDate) {
        this.frontDegreeApplyEndDate = frontDegreeApplyEndDate;
    }

    public Date getGraduateApplyStartDate() {
        return graduateApplyStartDate;
    }

    public void setGraduateApplyStartDate(Date graduateApplyStartDate) {
        this.graduateApplyStartDate = graduateApplyStartDate;
    }

    public Date getGraduateApplyEndDate() {
        return graduateApplyEndDate;
    }

    public void setGraduateApplyEndDate(Date graduateApplyEndDate) {
        this.graduateApplyEndDate = graduateApplyEndDate;
    }

    public Date getGraduateCheckStartDate() {
        return graduateCheckStartDate;
    }

    public void setGraduateCheckStartDate(Date graduateCheckStartDate) {
        this.graduateCheckStartDate = graduateCheckStartDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTable timeTable = (TimeTable) o;
        return Objects.equals(getId(), timeTable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
