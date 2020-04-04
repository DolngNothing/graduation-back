package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.School;
import com.scau.lzh.emste_graduation.service.CityAndDistrictService;
import com.scau.lzh.emste_graduation.service.MajorService;
import com.scau.lzh.emste_graduation.service.SchoolService;
import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {
    @Autowired
    private MajorService majorService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CityAndDistrictService cityAndDistrictService;


    @GetMapping("api/majors")
    public Result getMajorList() throws Exception {
        return Result.getSuccess("专业表",majorService.majorList());
    }

    @GetMapping("api/schools")
    public Result getSchoolList() throws Exception {
        return Result.getSuccess("学校",schoolService.getSchools());
    }

    @GetMapping("api/districts/{city}")
    public Result getDistrictList(@PathVariable("city") Long city) throws  Exception{
        return Result.getSuccess("地区",cityAndDistrictService.getDistricts(city));
    }
    @GetMapping("api/citys")
    public Result getCity() throws Exception{
        return Result.getSuccess("城市",cityAndDistrictService.getCitys());
    }
}
