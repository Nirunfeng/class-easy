package com.huachuan.classeasy.service.impl;

import com.huachuan.classeasy.common.StatusCode;
import com.huachuan.classeasy.common.error.ErrorCode;
import com.huachuan.classeasy.common.vo.BaseResult;
import com.huachuan.classeasy.repository.LoginRepository;
import com.huachuan.classeasy.repository.UserRoleRepository;
import com.huachuan.classeasy.repository.entity.Login;
import com.huachuan.classeasy.repository.entity.UserRole;
import com.huachuan.classeasy.service.LoginService;
import com.huachuan.classeasy.util.*;
import com.huachuan.classeasy.vo.LoginVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/27 23:05
 **/
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private EntityManager entityManager;

    @Value("${salt}")
    private String salt;

    /**
     * 添加登录用户
     * @param loginVO
     * @return
     */
    @Override
    public Boolean addLogin(LoginVO loginVO){
        try{
            /*转换do*/
            Login login=new Login();
            BeanUtils.copyProperties(login,loginVO);
            if (StringUtils.isEmpty(login.getId())){
                /*生成id*/
                login.setId(KeyUtil.genUniqueKey());
            }
            /*保存login*/
            loginRepository.save(login);
            return true;
        }catch (Exception e){
            logger.error(ErrorCode.ERROR_CODE_ADD_LOGIN.getMsg(),e);
            return false;
        }
    }

    /**
     * 判断登录方法
     * @param loginVO
     * @param session
     * @return
     */
    @Override
    public BaseResult userLogin(LoginVO loginVO, HttpSession session) {
        try{
            UsernamePasswordToken token;
            /*判断username类型*/
            String username="";
            if (!PhoneUtil.justPhone(loginVO.getUserName())){
                //输入的是用户名
                username = loginVO.getUserName();
            }else {
                //输入的是手机号
                username= loginVO.getUserName();
                loginVO.setPhone(username);
                //将封装的login中username变为null
                loginVO.setUserName(null);
            }
            //盐加密
            token=new UsernamePasswordToken(username, new Md5Hash(loginVO.getPassword(),salt).toString());

            /*shiro登录认证*/
            Subject subject= SecurityUtils.getSubject();
            subject.login(token);

            //获取数据库中的用户
            String password = new Md5Hash(loginVO.getPassword(), "Campus-shops").toString();
            loginVO.setPassword(password);
            /*查询数据库用户*/
            Login login=getLoginUser(loginVO);

            /*查询登录权限*/
            UserRole userRole=userRoleRepository.findUserRoleByUserId(login.getUserId());
            if (userRole.getRoleId() == 2 || userRole.getRoleId() == 3){
                session.setAttribute("admin",login.getUserName());
                session.setAttribute("username",login.getUserName());
                return new BaseResult(true, StatusCode.OK,"登录成功");
            }
            return new BaseResult(true,StatusCode.ACCESSERROR,"权限不足");
        }catch (UnknownAccountException e){
            return new BaseResult(true,StatusCode.LOGINERROR,"用户名不存在");
        }catch (IncorrectCredentialsException e){
            return new BaseResult(true,StatusCode.LOGINERROR,"密码错误");
        }
    }

    /**
     * 查询数据库用户
     * @param loginVO
     * @return
     */
    @Override
    public Login getLoginUser(LoginVO loginVO){
        Login login=new Login();
        StringBuffer selectSql=new StringBuffer();
        StringBuffer whereSql=new StringBuffer();
        selectSql.append("select * from login where 1=1 ");
        whereSql= CommonSqlUtil.whereSql(loginVO);
        selectSql.append(whereSql).append(" limit 1");
        Query query=this.entityManager.createNativeQuery(selectSql.toString(),Login.class);
        List<Login> loginList=query.getResultList();
        if (!CollectionUtils.isEmpty(loginList)){
            login=loginList.get(0);
        }
        return login;
    }
}
