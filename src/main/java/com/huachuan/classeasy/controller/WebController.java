package com.huachuan.classeasy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 页面控制器
 * @Date 2023/2/27 22:55
 **/
@Controller
public class WebController {
    /**
     * 管理员跳转登录
     */
    @GetMapping("/admin")
    public String admintologin() {
        return "admin/login/login";
    }
}
