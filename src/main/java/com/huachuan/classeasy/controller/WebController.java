package com.huachuan.classeasy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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

    /**
     * 后台管理首页
     * */
    @GetMapping("/admin/index")
    public String adminIndex(HttpSession session, HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        String admin = (String) session.getAttribute("admin");
        /**拦截器：如果不是管理员，则进行重定向*/
        if (StringUtils.isEmpty(admin)){
            response.sendRedirect(request.getContextPath() + "/");//重定向
        }
        return "/admin/index";
    }
}
