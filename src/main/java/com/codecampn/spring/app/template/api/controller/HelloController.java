package com.codecampn.spring.app.template.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Value("${my-section.hello-message}")
    private String message;

    @GetMapping("/hello")
    public String sayHello() {
        log.info(message);
        return message;
    }

    @GetMapping("/hello")
    public String sayHello2() {
        log.info(message);
        return message + "2";
    }
}

