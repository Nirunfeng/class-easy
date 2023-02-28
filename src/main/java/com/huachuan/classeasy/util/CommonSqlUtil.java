package com.huachuan.classeasy.util;

import com.huachuan.classeasy.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/27 23:58
 **/
public class CommonSqlUtil {

    public static StringBuffer whereSql(LoginVO loginVO){
        StringBuffer whereSql=new StringBuffer();
        if (StringUtils.isNotEmpty(loginVO.getId())){
            whereSql.append(" and id='"+loginVO.getId()+"' ");
        }
        if (StringUtils.isNotEmpty(loginVO.getUserId())){
            whereSql.append(" and user_id='"+loginVO.getUserId()+"' ");
        }
        if (StringUtils.isNotEmpty(loginVO.getUserName())){
            whereSql.append(" and user_name='"+loginVO.getUserName()+"' ");
        }
        if (StringUtils.isNotEmpty(loginVO.getPassword())){
            whereSql.append(" and password='"+loginVO.getPassword()+"' ");
        }
        if (StringUtils.isNotEmpty(loginVO.getPhone())){
            whereSql.append(" and phone='"+loginVO.getPhone()+"' ");
        }
        return whereSql;
    }
}
