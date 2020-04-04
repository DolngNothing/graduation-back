package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.domain.Thesis;
import com.scau.lzh.emste_graduation.service.StudentService;
import com.scau.lzh.emste_graduation.service.ThesisService;
import com.scau.lzh.emste_graduation.utils.Result;
import com.scau.lzh.emste_graduation.utils.ThesisParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThesisController {
    @Autowired
    ThesisService thesisService;

    @Autowired
    StudentService studentService;

    @PostMapping("/api/thesis/{admissionId}/{state}")
    public Thesis save(@PathVariable("admissionId") String admissionId, @PathVariable("state") int state,@RequestBody Thesis thesis) throws Exception {
        Student student=studentService.findById(admissionId);
        if(student!=null){
            thesis.setStudent(student);
            thesis.setState(state);
            thesis=thesisService.save(thesis);
            return thesis;
        }
        return null;
    }

    @GetMapping("/api/thesis/year/{year}/city/{city}")
    public List<Thesis> findOneYearByCity(@PathVariable("year") int year,@PathVariable("city") String city) throws Exception {
        List<Thesis> thesisList=thesisService.findOneYearByCity(city,year);
        return thesisList;
    }

    @GetMapping("/api/thesis/{admissionId}")
    public Result findByAdmissionId(@PathVariable("admissionId") String admissionId) throws Exception {
        List<Thesis> thesisList=thesisService.findAllByAdmissionId(admissionId);
        if(thesisList==null||thesisList.isEmpty()) return Result.getFail("没有论文");
        return Result.getSuccess("找到辣",thesisList);
    }

    @PostMapping("/api/thesis/spec")
    public List<Thesis> query(@RequestBody ThesisParam thesisParam){
        List<Thesis> thesisList=thesisService.queryTheses(thesisParam);
        return thesisList;
    }

    @GetMapping("api/thesises")
    public Result getAll()throws Exception{
        List<Thesis> thesisList=thesisService.findAll();
        if(thesisList==null||thesisList.isEmpty()) return Result.getFail("没有任何论文");
        return Result.getSuccess("成功",thesisList);
    }
}
