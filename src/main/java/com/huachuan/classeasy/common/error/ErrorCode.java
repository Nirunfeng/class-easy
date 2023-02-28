package com.huachuan.classeasy.common.error;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 错误码 0x10aaaa
 * @Date 2023/2/27 22:49
 **/
public enum ErrorCode {

    ERROR_CODE_ADD_LOGIN("0x100001","登录用户添加失败"),
    ERROR_CODE_VALIDATECODE("0x100003","验证码输入不正确"),
    ERROR_CODE_ADMIN_LOGIN("0x100002","后台用户登录失败");


    private final String errorCode;
    private final String errorMessage;

    private ErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public String getCode() {
        return errorCode;
    }
    public String getMsg() {
        return errorMessage;
    }
}
