package com.zood.springboot3demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 示例配置属性类。
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    /**
     * 应用名
     */
    @Value("${demo.name:springboot3-demo}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
