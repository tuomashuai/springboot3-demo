package com.zood.springboot3demo.controller;

import com.zood.springboot3demo.model.GreetingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例接口。
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot 3!";
    }

    @GetMapping("/greet/{name}")
    public GreetingResponse greet(@PathVariable String name) {
        return new GreetingResponse("Hello, " + name + "!");
    }

}
