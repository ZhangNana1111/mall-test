package com.macro.mall.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Controller层的日志封装类
 * @Author Zhangnana
 * @DATE 2020/12/12 9:53
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Weblog {

    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String userName;

    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * uri
     */
    private String URI;

    /**
     * url
     */
    private String URL;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求ip
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 返回结果
     */
    private Object returnResult;

}
