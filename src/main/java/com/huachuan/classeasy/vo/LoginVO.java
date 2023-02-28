package com.huachuan.classeasy.vo;

import com.huachuan.classeasy.repository.entity.Login;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/27 23:14
 **/
@Data
@ApiModel("登录参数")
public class LoginVO extends Login {

    /**
     * 验证码
     */
    private String verCode;
}
