package com.zood.springboot3demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * BCrypt 密码加密器单元测试。
 */
class PasswordConfigTest {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void encodesAndMatchesRawPassword() {
        String raw = "mySecret123";
        String hashed = encoder.encode(raw);

        // 密文不等于明文，且能通过校验
        assertNotEquals(raw, hashed);
        assertTrue(encoder.matches(raw, hashed));
    }

    @Test
    void rejectsWrongPassword() {
        String hashed = encoder.encode("correct-password");
        assertFalse(encoder.matches("wrong-password", hashed));
    }

    @Test
    void generatesDifferentSaltEachTime() {
        // 同一明文每次加密结果不同（随机盐）
        String first = encoder.encode("same-input");
        String second = encoder.encode("same-input");
        assertNotEquals(first, second);
        assertTrue(encoder.matches("same-input", first));
        assertTrue(encoder.matches("same-input", second));
    }
}