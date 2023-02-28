package com.huachuan.classeasy.controller;

import com.huachuan.classeasy.common.vo.BaseResult;
import com.huachuan.classeasy.repository.LoginRepository;
import com.huachuan.classeasy.repository.entity.Login;
import com.huachuan.classeasy.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/27 22:17
 **/
@RestController
@RequestMapping("/test")
@Api(tags = "测试controller")
public class TestController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/loginUser")
    @ApiOperation("测试用户查询")
    public BaseResult testLogin(){
        List<Login> logins=loginRepository.findAll();
        System.out.println(JsonUtil.toJson(logins));
        return new BaseResult();
    }
}
