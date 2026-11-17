package com.zood.springboot3demo.service;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zood.springboot3demo.entity.SysUser;
import com.zood.springboot3demo.mapper.SysUserMapper;
import com.zood.springboot3demo.model.dto.LoginRequest;
import com.zood.springboot3demo.model.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 登录认证服务。
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final SysUserMapper sysUserMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 用户名密码登录，成功后写入 Sa-Token 会话。
     */
    @Transactional(rollbackFor = Exception.class)
    public LoginResponse login(LoginRequest request) {
        SysUser user = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUsername, request.getUsername()));

        if (user == null || !StringUtils.hasText(user.getPassword())
                || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            // 统一提示，避免用户名/密码分别报错导致用户名枚举
            throw new IllegalArgumentException("用户名或密码错误");
        }
        if (user.getStatus() != null && user.getStatus() == 0) {
            throw new IllegalArgumentException("账号已被禁用，请联系管理员");
        }

        // Sa-Token 登录，device 区分登录端
        StpUtil.login(user.getId(), new SaLoginModel()
                .setDevice("web")
                .setExtra("username", user.getUsername())
                .setExtra("nickname", user.getNickname()));

        return new LoginResponse(
                StpUtil.getTokenName(),
                StpUtil.getTokenValue(),
                new LoginResponse.UserInfo(user.getId(), user.getUsername(), user.getNickname(), user.getAvatar()));
    }

    /**
     * 退出登录。
     */
    public void logout() {
        StpUtil.logout();
    }

    /**
     * 获取当前登录用户。
     */
    public SysUser currentUser() {
        Object loginId = StpUtil.getLoginIdDefaultNull();
        if (loginId == null) {
            return null;
        }
        return sysUserMapper.selectById(Long.valueOf(loginId.toString()));
    }
}
