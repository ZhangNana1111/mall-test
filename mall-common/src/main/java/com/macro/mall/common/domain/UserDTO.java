package com.macro.mall.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录实体类
 * @Author Zhangnana
 * @DATE 2020/12/10 9:38
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private Integer status;
    private String clientID;
    private List<String> rules;


    public static void main(String[] args) {
        List<UserDTO> usersList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            UserDTO userDTO = new UserDTO();
            usersList.add(userDTO);
            userDTO.setClientID(i + "df");
        }
        System.out.println(usersList.toString());
    }
}

