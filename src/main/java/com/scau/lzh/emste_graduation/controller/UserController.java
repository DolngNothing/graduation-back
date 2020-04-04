package com.scau.lzh.emste_graduation.controller;

import com.scau.lzh.emste_graduation.domain.Student;
import com.scau.lzh.emste_graduation.domain.User;
import com.scau.lzh.emste_graduation.repository.UserRepository;
import com.scau.lzh.emste_graduation.service.StudentService;
import com.scau.lzh.emste_graduation.service.UserService;
import com.scau.lzh.emste_graduation.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @PostMapping("api/user")
    public String save(@RequestBody User user) throws Exception {
        return userService.save(user);
    }

    /**
     * 将学生和用户绑定在一起
     * @param account
     * @param admissionId
     * @return
     */
    @PutMapping("api/bindStudent")
    public String bindStudent(@RequestParam(value = "account") String account ,
                            @RequestParam(value = "admissionId") String admissionId) throws Exception {
        User user=userService.findByAccount(account);
        Student student = studentService.findById(admissionId);
        if(student!=null&&user!=null){
            student.setUser(user);
            studentService.save(student);
            return "success";
        }
        else {
            return "fail";
        }
    }

    @GetMapping("api/users")
    public List<User> getAlluser() throws Exception {
        List<User> userList=userService.userList();
        return userList;
    }

    @GetMapping("api/user/{account}")
    public User findByAccount(String account)throws Exception{
        User user=userService.findByAccount(account);
        return user;
    }

    @PostMapping("api/login")
    public Result<User> login(@RequestBody User user) throws Exception {
        String account = user.getAccount();
        String password = user.getPassword();

        user=userService.findByAccount(account);

        if(user!=null&&user.getPassword().equals(password)){
            return Result.getSuccess("登陆成功",user);
        }
        else{
            return Result.getFail("登录失败");
        }
    }
}
