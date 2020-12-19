package com.macro.mall.common.api;

/**
 * 枚举一些常用API操作码
 * @Author Zhangnana
 * @DATE 2020/12/10 12:38
 * @Version 1.0
 */
public enum  ResultCode implements IErrorCode{

    // TODO 可以将返回code和message在配置文件中定义，直接读取配置文件中的数据即可。
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    /**
     * 返回码
     */
    private long code;
    /**
     * 返回信息
     */
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
