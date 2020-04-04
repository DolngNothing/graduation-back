package com.scau.lzh.emste_graduation.utils;

import com.scau.lzh.emste_graduation.domain.Major;
import com.scau.lzh.emste_graduation.domain.School;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ThesisParam {
    private Major major;
    private School school;

    private Date date;

    public ThesisParam() {
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date setEndTime(Date date){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.YEAR,c.get(Calendar.YEAR)+1);
        c.set(Calendar.MONTH,1);
        c.set(Calendar.DATE,1);
        return c.getTime();
    }

    public Date setStartTime(Date date){
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.YEAR,c.get(Calendar.YEAR)-1);
        c.set(Calendar.MONTH,12);
        c.set(Calendar.DATE,31);
        return c.getTime();
    }
}
