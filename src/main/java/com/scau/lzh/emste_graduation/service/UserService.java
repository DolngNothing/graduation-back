package com.scau.lzh.emste_graduation.service;

import com.scau.lzh.emste_graduation.domain.User;
import com.scau.lzh.emste_graduation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> userList() throws Exception{
        //这里可以使用Sort类进行排序或者分类
        return userRepository.findAll();
    }

    public String save(User user) throws Exception{
        if(userRepository.existsById(user.getAccount())){
            return "user existed";
        }
        else {
            userRepository.save(user);
            return "successe";
        }
    }

    public User findByAccount(String account) throws Exception{
        return userRepository.findById(account).orElse(null);
    }


}
