package com.scau.lzh.emste_graduation.test;

import com.scau.lzh.emste_graduation.domain.User;
import com.scau.lzh.emste_graduation.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void test(){
        User user=new User("201622222","LIZEHAO678");
        userRepository.save(user);
    }
}
