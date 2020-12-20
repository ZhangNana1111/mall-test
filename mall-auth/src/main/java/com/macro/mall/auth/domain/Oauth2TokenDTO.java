package com.macro.mall.auth.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Oauth2获取Token返回信息封装
 * @Author Zhangnana
 * @DATE 2020/12/20 8:36
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
@Builder
public class Oauth2TokenDTO {

    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("刷令牌")
    private String refreshToken;
    @ApiModelProperty("访问令牌头前缀")
    private String tokenHead;
    @ApiModelProperty("有效时间(秒)")
    private int expiresIn;
}
