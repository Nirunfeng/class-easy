package com.huachuan.classeasy.repository;

import com.huachuan.classeasy.repository.entity.UserPerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/28 0:33
 **/
@Repository
public interface UserPermRepository extends JpaRepository<UserPerm,Integer> {

    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    List<UserPerm> findUserPermByRoleId(Integer roleId);
}
