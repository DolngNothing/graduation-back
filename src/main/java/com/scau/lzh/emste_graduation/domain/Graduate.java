package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.awt.print.Pageable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "graduate")
@ApiModel("毕业生AND证书")
public class Graduate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "educationLevel")
    private String educationLevel;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    @JoinColumn(name = "admissionId")
    private Student student;

    @JsonFormat (pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    @Column(name = "graduateDate")
    private Date graduateDate;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "graduateMajor",referencedColumnName = "id")
    private Major major;

    @ManyToOne
    @JoinColumn(name = "school" , referencedColumnName = "id")
    private School school;

    @Column(name = "certified")
    private boolean certified;

    public Graduate() {
    }

    public boolean isCertified() {
        return certified;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
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
        Graduate graduate = (Graduate) o;
        return Objects.equals(getId(), graduate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Graduate{" +
                "id=" + id +
                ", educationLevel='" + educationLevel + '\'' +
                ", student=" + student +
                '}';
    }
}
