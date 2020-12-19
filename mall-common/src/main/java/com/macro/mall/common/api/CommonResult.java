package com.macro.mall.common.api;

/**
 * 通用返回对象
 * @Author Zhangnana
 * @DATE 2020/12/12 11:00
 * @Version 1.0
 */
public class CommonResult<T> {
    /**
     * 返回码
     */
    private long code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    protected CommonResult(){
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功 返回结果
     * @param data 返回数据
     * @param <T>
     * @return
     */
    public static<T>  CommonResult<T> success(T data){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 成功 返回结果
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data, String message){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败 返回结果
     * @param code
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> fail(IErrorCode code){
        return new CommonResult<>(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMessage(),null);
    }

    public static <T> CommonResult<T> fail(String message){
        return new CommonResult<>(ResultCode.FAILED.getCode(), message,null);
    }

    /**
     * 失败 返回结果
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> fail(IErrorCode code, String message){
        return new CommonResult<>(ResultCode.FAILED.getCode(), message,null);
    }

    /**
     * 校验参数失败 返回结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> validateFailed(String message){
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录 返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unLogin(T data){
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    public static <T> CommonResult<T> forbidden(T data){
        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
