package com.macro.mall.auth.exception;

import com.macro.mall.common.api.CommonResult;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局处理Oauth2抛出的异常
 * @Author Zhangnana
 * @DATE 2020/12/20 8:39
 * @Version 1.0
 */

@ControllerAdvice
public class Oauth2ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class )
    public CommonResult handleOauth2(OAuth2Exception e){
        return CommonResult.fail(e.getMessage());
    }
}
