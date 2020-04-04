package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.TimeTable;
import com.scau.lzh.emste_graduation.service.TimeTableService;
import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;

@RestController
public class TimeTableController {
    @Autowired
    private TimeTableService timeTableService;

    @PostMapping("/api/timeTable")
    public Result save(@RequestBody TimeTable timeTable)throws Exception{
        timeTable=timeTableService.save(timeTable);
        return Result.getSuccess("成功保存时间表");
    }

    @GetMapping("/api/timeTable")
    public Result get() throws Exception {
        TimeTable timeTable=timeTableService.find();
        if(timeTable==null) return Result.getFail("没有设置过时间表");
        return Result.getSuccess("成功",timeTable);
    }
}
