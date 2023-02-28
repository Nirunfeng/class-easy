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
 * @Date 2023/2/28 0:11
 **/
@Data
@Table(name = "user_role")
@Entity
@Accessors(chain = true)
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private String userId;
    /**
     * 1普通用户 2管理员 3超级管理员
     */
    @Column(name = "role_id")
    private Integer roleId;
    /**
     * 身份
     */
    @Column(name = "identity")
    private String identity;
}
