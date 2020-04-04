package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "graduationApply")
@ApiModel("毕业申请与处理表")
public class GraduationApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "submitDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submitDate;

    @ApiModelProperty(value = "审批进度 ：0：由区审批；1：由市审批；2：由省审批；3：审批通过 4：不通过")
    @Column(name = "applyState")
    private int applyState;

    @ManyToOne
    @JoinColumn(name = "applyMajorId",referencedColumnName = "id")
    private Major major;

    @ManyToOne
    @JoinColumn(name = "admissionId",referencedColumnName = "admissionId")
    private Student student;

    @Column(name = "graduateDate")
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date graduateDate;

    //下面是申请成功后的处理信息

    @OneToOne
    @JoinColumn(name = "graduateId",referencedColumnName = "id")
    private Graduate graduate;

    //可去领取的时间
    @Column(name = "receiveStartDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveStartDate;

    //可去领取结束的时间
    @Column(name = "receiveEndDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveEndDate;

    @Column(name = "receivePosition")
    private String receivePosition;

    @ApiModelProperty(value = "毕业证进度 ：0：待打印；1：已打印，待发放；2：已发放，待领取；3：已领取 4:被撤销毕业")
    @Column(name = "certificateState")
    private Integer certificateState;

    public GraduationApply() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public int getApplyState() {
        return applyState;
    }

    public void setApplyState(int applyState) {
        this.applyState = applyState;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Date getReceiveStartDate() {
        return receiveStartDate;
    }

    public void setReceiveStartDate(Date receiveStartDate) {
        this.receiveStartDate = receiveStartDate;
    }

    public Date getReceiveEndDate() {
        return receiveEndDate;
    }

    public void setReceiveEndDate(Date receiveEndDate) {
        this.receiveEndDate = receiveEndDate;
    }

    public String getReceivePosition() {
        return receivePosition;
    }

    public void setReceivePosition(String receivePosition) {
        this.receivePosition = receivePosition;
    }

    public Integer getCertificateState() {
        return certificateState;
    }

    public void setCertificateState(Integer certificateState) {
        this.certificateState = certificateState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraduationApply that = (GraduationApply) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "GraduationApply{" +
                "id=" + id +
                ", submitDate=" + submitDate +
                ", applyState=" + applyState +
                ", graduateDate=" + graduateDate +
                ", receiveStartDate=" + receiveStartDate +
                ", receiveEndDate=" + receiveEndDate +
                ", receivePosition='" + receivePosition + '\'' +
                ", certificateState=" + certificateState +
                '}';
    }
}
