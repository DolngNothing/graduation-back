package com.scau.lzh.emste_graduation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.util.Objects;

@JsonIgnoreProperties("student")
@Entity
@Table(name = "user")
@ApiModel("用户实体")
public class User {
    @Id
    @Column(name = "account")
    private String account;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "city",referencedColumnName = "id")
    private City city;

    @Column(name = "province")
    private String province;

    @ManyToOne
    @JoinColumn(name = "district",referencedColumnName = "id")
    private District district;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user" ,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    @JsonBackReference
    private Student student;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public User() {
    }

    public User(String acount, String password) {
        this.account = acount;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String acount) {
        this.account = acount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getAccount(), user.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccount());
    }

}
