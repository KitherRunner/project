package com.kither.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "hello", produces = "text/html;charset=utf-8")
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
