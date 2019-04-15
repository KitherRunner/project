package com.kither.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("manualBatch")
public class ManualBatch {

    // 获取批处理job对象
    @Autowired
    private Job job;

    // 获取批处理执行器对象
    @Autowired
    private JobLauncher jobLauncher;

    // 执行批处理
    public void startBatchJob() throws Exception {
        // 设置批处理启动对象
        JobParameter jobParameter = new JobParameter(new Date());
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("date", jobParameter);
        JobExecution run = jobLauncher.run(job, new JobParameters(jobParameterMap));
        System.out.println("status: " + run.getStatus());
    }
}
