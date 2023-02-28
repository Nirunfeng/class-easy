package com.huachuan.classeasy.config.shiro;

import com.huachuan.classeasy.repository.UserPermRepository;
import com.huachuan.classeasy.repository.UserRoleRepository;
import com.huachuan.classeasy.repository.entity.Login;
import com.huachuan.classeasy.repository.entity.UserPerm;
import com.huachuan.classeasy.repository.entity.UserRole;
import com.huachuan.classeasy.service.LoginService;
import com.huachuan.classeasy.util.BeanUtils;
import com.huachuan.classeasy.util.JsonUtil;
import com.huachuan.classeasy.util.PhoneUtil;
import com.huachuan.classeasy.vo.LoginVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 自定义realm
 * @Date 2023/2/27 22:57
 **/
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserPermRepository userPermRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private LoginService loginService;

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //到数据库查询当前用户的授权的字符串
        Subject subject= SecurityUtils.getSubject();
        Login login =(Login) subject.getPrincipal();
        UserRole userRole = userRoleRepository.findUserRoleByUserId(login.getUserId());
        List<UserPerm> userPerms=userPermRepository.findUserPermByRoleId(userRole.getRoleId());
        if (!CollectionUtils.isEmpty(userPerms)){
            List<String> permsList=new ArrayList<>();
            userPerms.forEach(userPerm -> {
                if (StringUtils.isNotEmpty(userPerm.getPerms())){
                    permsList.add(userPerm.getPerms());
                }
            });
            info.addStringPermissions(permsList);
        }

        return info;
    }

    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //编写shiro判断逻辑，判断用户名和密码
        //1、判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        Login login = new Login();
        //如果传入的是用户名
        if (!PhoneUtil.justPhone(token.getUsername())) {
            login.setUserName(token.getUsername());
        }else {//如果传入的是手机号
            login.setPhone(token.getUsername());
        }
        LoginVO loginVO=new LoginVO();
        BeanUtils.copyProperties(loginVO,login);
        Login Login1=loginService.getLoginUser(loginVO);
        if(Login1==null){
            //用户不存在
            return null;//shiro底层抛出UnknownAccountException
        }
        //2、判断密码 三个参数：1、返回给subject.login(token);方法的参数  2、数据库中的密码 3、shiro的名字
        return new SimpleAuthenticationInfo(Login1,Login1.getPassword(),"");
    }
}
