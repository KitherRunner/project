package com.kither;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
// 启动eureka服务中心
@EnableEurekaServer
public class SpringclouddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringclouddemoApplication.class, args);
    }

}
