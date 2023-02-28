package com.huachuan.classeasy.service;

import com.huachuan.classeasy.common.vo.BaseResult;
import com.huachuan.classeasy.repository.entity.Login;
import com.huachuan.classeasy.vo.LoginVO;

import javax.servlet.http.HttpSession;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/27 23:03
 **/
public interface LoginService {

    public Boolean addLogin(LoginVO loginVO);

    /**
     * 判断登录方法
     * @param loginVO
     * @param session
     * @return
     */
    BaseResult userLogin(LoginVO loginVO, HttpSession session);

    Login getLoginUser(LoginVO loginVO);
}
