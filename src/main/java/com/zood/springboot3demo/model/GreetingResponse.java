package com.zood.springboot3demo.model;

/**
 * 示例响应体。
 *
 * @param message 返回给调用方的消息
 */
public record GreetingResponse(String message) {
}
