package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.TimeTable;
import com.scau.lzh.emste_graduation.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableService {
    @Autowired
    private TimeTableRepository timeTableRepository;

    public TimeTable save(TimeTable timeTable)throws Exception{
        try {
            return timeTableRepository.saveAndFlush(timeTable);
        } catch (Exception e) {
            System.out.println("保存时间表失败");
        }
        return null;
    }

    public TimeTable find()throws Exception{
        try {
            List<TimeTable> timeTables=timeTableRepository.findAll();
            if(timeTables==null||timeTables.isEmpty()) return null;
            return timeTables.get(0);
        } catch (Exception e) {
            System.out.println("获取时间表失败");
        }
        return null;
    }
}
