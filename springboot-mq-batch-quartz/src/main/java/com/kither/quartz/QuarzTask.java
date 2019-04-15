package com.kither.quartz;


import com.kither.batch.ManualBatch;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.TimeZone;

// springboot使用quartz实现定时任务
@Configuration
public class QuarzTask {

    @Autowired
    private ManualBatch manualBatch;

    // 设置定时任务执行线程池
    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(20);
        // 设置最大线程数
        executor.setMaxPoolSize(100);
        executor.setKeepAliveSeconds(60);
        // 设置队列容量
        executor.setQueueCapacity(150);
        return executor;
    }

    // 方法调用工程Bean
    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        // 是否允许并发
        factoryBean.setConcurrent(false);
        // 目标对象
        factoryBean.setTargetObject(manualBatch);
        // 目标方法
        factoryBean.setTargetMethod("startBatchJob");
        return factoryBean;
    }

    // 定时任务触发器Bean
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        // 设置定时器7秒执行一次
        factoryBean.setCronExpression("0/7 * * * * ?");
        // 绑定对应的jobDetail
        factoryBean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        // 设置时区
        factoryBean.setTimeZone(TimeZone.getDefault());
        // 设置延时启动时间,10s
        factoryBean.setStartDelay(10000);
        return factoryBean;
    }

    // 设置定时任务执行schdule
    // 这里可以把需要使用的触发器通过参数传递进来，对应的CronTrigger会通过cronTriggerFactoryBean获取
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTrigger cronTrigger) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        // 绑定触发器
        factoryBean.setTriggers(cronTrigger);
        // 绑定执行线程池
        factoryBean.setTaskExecutor(executor());
        return factoryBean;
    }
}
