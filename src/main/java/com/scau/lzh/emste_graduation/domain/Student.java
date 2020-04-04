package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "student")
@ApiModel("学生实体")
public class Student {

    /**
     * 准考证号：由市编码+区县编码+年份后两位+学校编码+随机编码4位
     */
    @Id
    @Column(name = "admissionId",unique = true)
    private String admissionId;

    @ManyToOne
    @JoinColumn(name = "schoolId",referencedColumnName = "id")
    private School school;

    @Column(name = "stuType")
    private String stuType;

    @Column(name = "photo")
    private String photo;

    @Column(name = "idCard",unique = true,nullable = false)
    private String idCard;

    @Column(name = "education")
    private String education;

    @Column(name = "address")
    private String address;

    @Column(name = "postCode")
    private String postCode;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNum")
    private String phoneNum;

    /**
     * 是否专业毕业 PS：可以从其他专业也毕业，但不是本专业
     */
    @Column(name = "isMajorGraduated")
    private boolean isMajorGraduated=false;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "applicationDate")
    private Date applicationDate;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "majorId",referencedColumnName = "id")
    private Major major;

    /**
     * Cascade detach operation，级联脱管/游离操作。
     * 如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
     */

    @OneToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "account")
    private User user;

    /**
     * cascade用在这个实体中，代表着，删除这个主实体会影响下面的这个副实体的那些地方
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ceGradeId" ,referencedColumnName = "id")
    private CEgradeInfo cEgradeInfo;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "statusId",referencedColumnName = "id")
    private Status status;

    /**
     * 一个学生有多个成绩表，删除学生时也会删除掉成绩表
     */

    public Student() {
    }

    public Student(String admissionId) {
        this.admissionId = admissionId;
    }

    public String getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(String admissionId) {
        this.admissionId = admissionId;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public boolean isMajorGraduated() {
        return isMajorGraduated;
    }

    public void setMajorGraduated(boolean majorGraduated) {
        isMajorGraduated = majorGraduated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CEgradeInfo getcEgradeInfo() {
        return cEgradeInfo;
    }

    public void setcEgradeInfo(CEgradeInfo cEgradeInfo) {
        this.cEgradeInfo = cEgradeInfo;
    }

    public String getStuType() {
        return stuType;
    }

    public void setStuType(String stuType) {
        this.stuType = stuType;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getAdmissionId(), student.getAdmissionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdmissionId());
    }

    @Override
    public String toString() {
        return "Student{" +
                "admissionId='" + admissionId + '\'' +
                '}';
    }
}
