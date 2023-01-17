package com.codecampn.springbootsample.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String getHelloWorld(@RequestParam Optional<String> name) {
        return "Hello " + name.orElse("world") + "!";
    }
}
