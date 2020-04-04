package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.swing.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "thesis")
@ApiModel("论文")
public class Thesis {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "majorId",referencedColumnName = "id")
    private Major major;

    @Column(name = "passDate")
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date passDate;

    @Column(name = "title")
    private String title;

    @Column(name = "context")
    private String context;

    @ApiModelProperty(value = "0:未过 1：过了")
    @Column(name = "state")
    private int state;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "admissionId")
    private Student student;

    public Thesis() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
        Thesis thesis = (Thesis) o;
        return Objects.equals(getId(), thesis.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
