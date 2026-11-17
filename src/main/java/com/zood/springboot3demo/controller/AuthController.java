package com.zood.springboot3demo.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.zood.springboot3demo.entity.SysUser;
import com.zood.springboot3demo.model.dto.LoginRequest;
import com.zood.springboot3demo.model.dto.LoginResponse;
import com.zood.springboot3demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录认证接口。
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 登录。
     */
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    /**
     * 退出登录。
     */
    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }

    /**
     * 获取当前登录用户信息。
     */
    @GetMapping("/me")
    public SysUser me() {
        return authService.currentUser();
    }

    /**
     * 当前登录用户 id（演示 Sa-Token 用法）。
     */
    @GetMapping("/check")
    public String check() {
        return "loginId=" + StpUtil.getLoginId() + ", token=" + StpUtil.getTokenValue();
    }
}
