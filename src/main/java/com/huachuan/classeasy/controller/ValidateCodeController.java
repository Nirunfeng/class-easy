package com.huachuan.classeasy.controller;

import com.huachuan.classeasy.util.ValidateCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2023/2/28 20:23
 **/
@RestController
@Api(tags = "验证码控制器")
public class ValidateCodeController {

    @RequestMapping(value = "/images", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("验证码生成")
    public void images(HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ValidateCode vCode = new ValidateCode(820, 200, 5, 80);
        vCode.write(response.getOutputStream());
    }
}
