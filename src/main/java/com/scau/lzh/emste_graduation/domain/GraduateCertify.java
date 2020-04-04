package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "graduateCertify")
@ApiModel("毕业证明")
public class GraduateCertify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "1:遗失证明 2:提前证明 3:出国留学证明")
    @Column(name = "certifyType")
    private Integer certifyType;

    @Column(name = "applyDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyDate;

    @OneToOne
    @JoinColumn(name = "graduateId",referencedColumnName = "id")
    private Graduate graduate;

    @ManyToOne
    @JoinColumn(name = "admissionId")
    private Student student;

    @OneToOne
    @ApiModelProperty(value = "开证明的负责人")
    @JoinColumn(name = "charge",referencedColumnName = "account")
    private User user;

    public GraduateCertify() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCertifyType() {
        return certifyType;
    }

    public void setCertifyType(Integer certifyType) {
        this.certifyType = certifyType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraduateCertify that = (GraduateCertify) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
