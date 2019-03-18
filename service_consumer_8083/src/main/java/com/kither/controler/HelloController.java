package com.kither.controler;

import com.kither.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("hello2/{name}")
    private String sayHello(@PathVariable("name") String name) {
        return helloService.sayHello(name);
    }
}
