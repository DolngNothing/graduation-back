package com.scau.lzh.emste_graduation.repository;

import com.scau.lzh.emste_graduation.domain.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
