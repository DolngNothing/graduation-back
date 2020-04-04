package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 毕业预提
 */
@Entity
@Table(name = "withHoldingGraduation")
@ApiModel("毕业预提实体")
public class WithHoldingGraduation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "admissionId" ,unique = true)
    private Student student;

    @Column(name = "withHoldingDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date withHoldingDate;

    public WithHoldingGraduation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getWithHoldingDate() {
        return withHoldingDate;
    }

    public void setWithHoldingDate(Date withHoldingDate) {
        this.withHoldingDate = withHoldingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithHoldingGraduation that = (WithHoldingGraduation) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "WithHoldingGraduation{" +
                "id=" + id +
                ", student=" + student +
                '}';
    }
}
