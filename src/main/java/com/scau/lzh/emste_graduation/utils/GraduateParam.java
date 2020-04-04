package com.scau.lzh.emste_graduation.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GraduateParam {
    private String city;
    private String district;
    private Long majorId;

    private Date graduateDate;

    private Date graduateYear;

    public GraduateParam() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }

    public Date getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(Date graduateYear) {
        this.graduateYear = graduateYear;
    }

    public Date getOneStartTime(Date date){
        Calendar c = new GregorianCalendar();
        c.setTime(date);

        c.set(Calendar.YEAR,c.get(Calendar.YEAR));
        c.set(Calendar.MONTH,c.get(Calendar.MONTH)-1);
        c.set(Calendar.DATE,1);

        return c.getTime();
    }

    public Date getOneEndTime(Date date){
        Calendar c = new GregorianCalendar();
        c.setTime(date);

        if(c.get(Calendar.MONTH)>10){
            c.set(Calendar.YEAR,c.get(Calendar.YEAR)+1);
            c.set(Calendar.MONTH,1);
            c.set(Calendar.DATE,1);
        }
        else {
            c.set(Calendar.YEAR,c.get(Calendar.YEAR));
            c.set(Calendar.MONTH,c.get(Calendar.MONTH)+1);
            c.set(Calendar.DATE,27);
        }

        return c.getTime();
    }

    public Date getYearStartTime(Date date){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.YEAR,c.get(Calendar.YEAR));
        c.set(Calendar.MONTH,c.get(Calendar.MONTH)-1);
        return c.getTime();
    }

    public Date getYearEndTime(Date date){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.YEAR,c.get(Calendar.YEAR)+1);
        c.set(Calendar.MONTH,1);
        c.set(Calendar.DATE,27);
        return c.getTime();
    }
}
