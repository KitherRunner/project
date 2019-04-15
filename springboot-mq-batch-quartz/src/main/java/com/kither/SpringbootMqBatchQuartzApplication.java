package com.kither;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// 开启定时任务
@EnableScheduling
// 开启批处理
@EnableBatchProcessing
public class SpringbootMqBatchQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMqBatchQuartzApplication.class, args);
    }

}
