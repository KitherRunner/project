package com.kither.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// name:远程服务名，及spring.application.name配置的名称
@FeignClient(name = "service-provider")
public interface ConsumerService {

    @GetMapping("hello")
    String sayHello(@RequestParam("name") String name);
}
