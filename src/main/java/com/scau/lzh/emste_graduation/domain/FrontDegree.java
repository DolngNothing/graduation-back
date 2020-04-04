package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.naming.Name;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "frontDegree")
@ApiModel("前置学历申请")
public class FrontDegree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "graduateId",referencedColumnName = "id")
    private Graduate graduate;

    @Column(name = "submitDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submitDate;

    @ApiModelProperty(value = "前置学历审核进度 ：0：审核中 1：通过  2：不通过 3：被撤销")
    @Column(name = "state")
    private int state;

    @OneToOne
    @JoinColumn(name = "checkAdmin",referencedColumnName = "account")
    @ApiModelProperty(value = "审批的管理员")
    private User user;

    @ManyToOne
    @JoinColumn(name = "admissionId")
    private Student student;

    public FrontDegree() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrontDegree that = (FrontDegree) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "FrontDegree{" +
                "id=" + id +
                ", submitDate=" + submitDate +
                ", state=" + state +
                '}';
    }
}
