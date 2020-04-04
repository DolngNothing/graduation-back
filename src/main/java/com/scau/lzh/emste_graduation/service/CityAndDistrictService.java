package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.City;
import com.scau.lzh.emste_graduation.domain.District;
import com.scau.lzh.emste_graduation.repository.CityRepository;
import com.scau.lzh.emste_graduation.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityAndDistrictService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    public List<City> getCitys()throws Exception{
        try {
            Long shuzi=99l;
            return cityRepository.findAllByIdNot(shuzi);
        } catch (Exception e) {
            System.out.println("找城市失败");
        }
        return null;
    }

    public List<District> getDistricts(Long city)throws Exception{
        try {
            return districtRepository.findAllByCity_Id(city);
        } catch (Exception e) {
            System.out.println("找地区失败");
        }
        return null;
    }
}
