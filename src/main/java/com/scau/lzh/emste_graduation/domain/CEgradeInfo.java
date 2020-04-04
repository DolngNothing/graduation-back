package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cEgradeInfo")
@ApiModel("中英文成绩信息表")
public class CEgradeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "chineseGrade",nullable = false)
    private Double chineseGrade;

    @Column(name = "englishGrade",nullable = false)
    private Double englishGrade;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "passDate")
    private Date passDate;

   // @OneToOne(mappedBy = "cEgradeInfo",optional = false,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
  //  @JsonBackReference
   // private Student student;

    public CEgradeInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public Double getChineseGrade() {
        return chineseGrade;
    }

    public void setChineseGrade(Double chineseGrade) {
        this.chineseGrade = chineseGrade;
    }

    public Double getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(Double englishGrade) {
        this.englishGrade = englishGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CEgradeInfo that = (CEgradeInfo) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getChineseGrade(), that.getChineseGrade()) &&
                Objects.equals(getEnglishGrade(), that.getEnglishGrade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getChineseGrade(), getEnglishGrade());
    }

}
