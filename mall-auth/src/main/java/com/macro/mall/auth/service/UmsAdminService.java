package com.macro.mall.auth.service;

import com.macro.mall.common.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Zhangnana
 * @DATE 2020/12/20 16:06
 * @Version 1.0
 */
@FeignClient("mall-admin")
public interface UmsAdminService {
    @GetMapping("/admin/loadByUsername")
    UserDTO loadUserByUsername(@RequestParam String username);
}
