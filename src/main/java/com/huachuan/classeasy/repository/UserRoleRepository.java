package com.huachuan.classeasy.repository;

import com.huachuan.classeasy.repository.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/28 0:14
 **/
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {

    /**
     * 根据userId查询权限
     * @param userId
     * @return
     */
    UserRole findUserRoleByUserId(String userId);
}
