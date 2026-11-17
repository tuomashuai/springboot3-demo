-- ============================================================
-- springboot3-demo 数据库初始化脚本
-- 执行前请先创建数据库：CREATE DATABASE springboot3_demo DEFAULT CHARACTER SET utf8mb4;
-- ============================================================

CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`    VARCHAR(64)  NOT NULL COMMENT '登录用户名',
    `password`    VARCHAR(100) NOT NULL COMMENT 'BCrypt 加密后的密码',
    `nickname`    VARCHAR(64)           DEFAULT NULL COMMENT '昵称',
    `avatar`      VARCHAR(255)          DEFAULT NULL COMMENT '头像地址',
    `status`      TINYINT      NOT NULL DEFAULT 1 COMMENT '状态：0=禁用，1=正常',
    `version`     INT          NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
    `create_time` DATETIME              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统用户表';

-- ============================================================
-- 初始化管理员账号（明文密码：123456）
-- password 字段为 BCrypt(123456) 的哈希，
-- 如需修改密码请用工具重新生成 BCrypt 哈希后替换。
-- ============================================================
INSERT INTO `sys_user` (`username`, `password`, `nickname`, `status`)
SELECT 'admin', '$2a$10$AbC5X8fQO33RyrlkWS7h9Os/NPpPN2fsz0jSGbgsk1vDUFfuP1Qgy', '超级管理员', 1
WHERE NOT EXISTS (SELECT 1 FROM `sys_user` WHERE `username` = 'admin');
