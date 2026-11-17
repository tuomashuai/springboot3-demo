package com.zood.springboot3demo.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sa-Token 鉴权配置：全局登录校验 + 角色注解鉴权。
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器
        registry.addInterceptor(new SaInterceptor(handle -> SaRouter
                        .match("/api/**")
                        // 放行登录相关接口与公开的演示接口
                        .notMatch("/api/auth/login", "/api/auth/logout", "/api/hello")
                        .check(r -> StpUtil.checkLogin())))
                .addPathPatterns("/**");
    }
}
