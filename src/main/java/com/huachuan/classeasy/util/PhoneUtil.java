package com.huachuan.classeasy.util;

import java.util.regex.Pattern;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/27 23:47
 **/
public class PhoneUtil {

    /**
     * 校验是否为手机号
     * @param phoneNum
     * @return
     */
    public static boolean justPhone(String phoneNum){
        if(phoneNum.length()!=11){
            return false;//不符合规则的账号
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if(!pattern.matcher(phoneNum).matches()){//判断是否包含字符
            return false;//包含字符不是手机号
        }
        return true;
    }
}
