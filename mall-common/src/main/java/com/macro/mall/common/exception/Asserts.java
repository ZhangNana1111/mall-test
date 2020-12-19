package com.macro.mall.common.exception;

import com.macro.mall.common.api.IErrorCode;

/**
 * 用于抛出各种异常API
 * @Author Zhangnana
 * @DATE 2020/12/10 12:46
 * @Version 1.0
 */
public class Asserts {

    public static void fail(String message){
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode){
        throw new ApiException(errorCode);
    }
}
