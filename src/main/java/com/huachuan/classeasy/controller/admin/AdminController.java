package com.huachuan.classeasy.controller.admin;

import com.huachuan.classeasy.common.StatusCode;
import com.huachuan.classeasy.common.error.ErrorCode;
import com.huachuan.classeasy.common.vo.BaseResult;
import com.huachuan.classeasy.service.LoginService;
import com.huachuan.classeasy.service.UserInfoService;
import com.huachuan.classeasy.service.UserRoleService;
import com.huachuan.classeasy.util.JsonUtil;
import com.huachuan.classeasy.util.ValidateCode;
import com.huachuan.classeasy.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 后台管理员控制器
 * @Date 2023/2/27 22:56
 **/

@RestController
@RequestMapping("/admin")
@Api(tags = "管理员接口")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

//    @Autowired
//    private UserRoleService userRoleService;
    @Autowired
    private LoginService loginService;
//    @Autowired
//    private UserInfoService userInfoService;

    /**
     * 登录方法
     * @param loginVO
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("管理员登录接口")
    public BaseResult login(@RequestBody LoginVO loginVO,
                            HttpSession session){
        try{
            logger.info("登录参数:{}", JsonUtil.toJson(loginVO));
//            if(StringUtils.isNotEmpty(loginVO.getVerCode())&&!ValidateCode.code.equalsIgnoreCase(loginVO.getVerCode())){
//                /*校验码不一致直接返回错误*/
//                return new BaseResult(false, StatusCode.ERROR,ErrorCode.ERROR_CODE_VALIDATECODE.getMsg());
//            }
            /*判断登录方法*/
            return loginService.userLogin(loginVO,session);
        }catch (Exception e){
            logger.error(ErrorCode.ERROR_CODE_ADMIN_LOGIN.getMsg(),e);
            return new BaseResult(false,StatusCode.ERROR,
                    ErrorCode.ERROR_CODE_ADMIN_LOGIN.getMsg());
        }
    }
}
