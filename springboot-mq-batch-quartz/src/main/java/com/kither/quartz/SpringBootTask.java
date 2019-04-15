package com.kither.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SpringBootTask {

    // 使用springboot自带的定时任务，配置@EnableScheduling使用
    // 设置定时任务每5秒运行一次
    @Scheduled(cron = "0/5 * * * * ?")
    public void print() {
        System.out.println("springboot自带的定时任务" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

}

