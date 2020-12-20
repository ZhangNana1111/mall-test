package com.macro.mall.auth.service.impl;

import com.macro.mall.auth.constant.MessageConstant;
import com.macro.mall.auth.domain.SecurityUser;
import com.macro.mall.auth.service.UmsAdminService;
import com.macro.mall.auth.service.UmsMemberService;
import com.macro.mall.common.constant.AuthConstant;
import com.macro.mall.common.domain.UserDTO;
import javafx.fxml.LoadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理业务类
 * @Author Zhangnana
 * @DATE 2020/12/20 16:10
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        UserDTO userDTO;
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){
            userDTO = adminService.loadUserByUsername(username);
        }else {
            userDTO = memberService.loadUserByUsername(username);
        }
        if (userDTO == null){
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDTO.setClientID(clientId);
        SecurityUser securityUser = new SecurityUser();
        if (!securityUser.isEnabled()){
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        }else if (!securityUser.isAccountNonExpired()){
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        }else if (!securityUser.isAccountNonLocked()){
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        }else if (!securityUser.isCredentialsNonExpired()){
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }
}
