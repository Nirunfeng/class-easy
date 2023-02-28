package com.huachuan.classeasy.repository.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/28 0:30
 **/
@Data
@Table(name = "user_perm")
@Entity
@Accessors(chain = true)
public class UserPerm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 1普通用户 2管理员 3超级管理员
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    /**
     * 对应权限
     */
    @Column(name = "perms")
    private String perms;
    /**
     * 权限解释
     */
    @Column(name = "mean")
    private String mean;
}
