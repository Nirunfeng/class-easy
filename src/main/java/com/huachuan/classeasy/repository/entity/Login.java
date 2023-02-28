package com.huachuan.classeasy.repository.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @Description: 后台管理员账户
 * @Date 2023/2/27 21:51
 **/
@Table(name = "login")
@Data
@Entity
@Accessors(chain = true)//链式写法
@ApiModel("登录实体")
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录id
     */
    @Id
    @ApiModelProperty("id")
    private String id;
    /**
     * 用户id
     */

    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private String userId;
    /**
     * 角色id 1普通用户 2管理员 3超级管理员
     */
    @Column(name = "role_id")
    @ApiModelProperty("角色id 1普通用户 2管理员 3超级管理员")
    private Integer roleId;
    /**
     * 用户名
     */
    @Column(name = "user_name")
    @ApiModelProperty("用户名")
    private String userName;
    /**
     * 用户密码
     */
    @Column(name = "password")
    @ApiModelProperty("用户密码")
    private String password;
    /**
     * 手机号
     */
    @Column(name = "phone")
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 1正常 0封号
     */
    @Column(name = "user_status")
    @ApiModelProperty("1正常 0封号")
    private Integer userStatus;
}
