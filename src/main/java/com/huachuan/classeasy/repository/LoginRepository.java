package com.huachuan.classeasy.repository;

import com.huachuan.classeasy.repository.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/27 22:24
 **/

@Repository
public interface LoginRepository extends JpaRepository<Login,String> {
}
