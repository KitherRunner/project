package com.kither.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-provider")
public interface HelloService {

    @GetMapping("hello")
    public String sayHello(@RequestParam("name") String name);
}
