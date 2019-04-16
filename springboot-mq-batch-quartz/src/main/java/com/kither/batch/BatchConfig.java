package com.kither.batch;

import com.kither.bean.Student;
import com.kither.bean.User;
import com.kither.listener.ProcessorListener;
import com.kither.listener.ReaderListender;
import com.kither.listener.WriterListender;
import com.kither.processor.UserProcessor;
import com.kither.reader.UserReader;
import com.kither.writer.UserWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
// 在dataSource实例化后再实例化
@AutoConfigureAfter(DataSource.class)
public class BatchConfig {

    @Autowired
    private UserReader userReader;

    @Autowired
    private ReaderListender readerListender;

    @Autowired
    private UserProcessor userProcessor;

    @Autowired
    private ProcessorListener processorListener;

    @Autowired
    private UserWriter userWriter;

    @Autowired
    private WriterListender writerListender;

    // 设置jobRepository
    @Bean
    public JobRepositoryFactoryBean repositoryFactoryBean(DataSource dataSource, PlatformTransactionManager platformTransactionManager) {
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDatabaseType("mysql");
        factoryBean.setDataSource(dataSource);
        factoryBean.setTransactionManager(platformTransactionManager);
        return factoryBean;
    }

    // 设置job
    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        JobBuilderFactory factory = new JobBuilderFactory(jobRepository);
        return factory.get("kitherJob")
                .incrementer(new RunIdIncrementer()) // 设置一个自增的值，避免批处理因为参数相同而不能重复执行
                .listener(listener()) // 设置监听器
                .flow(step) // 指定step
                .end().build();
    }

    // 设置step
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        StepBuilderFactory factory = new StepBuilderFactory(jobRepository, platformTransactionManager);
        TaskletStep step = factory.get("kitherStep").<User, Student>chunk(3) // 每次提交3条数据
                .reader(userReader).listener(readerListender) // 设置reader及监听器
                .processor(userProcessor).listener(processorListener) // 设置processor及监听器
                .writer(userWriter).listener(writerListender).build(); // 设置writer及监听器
        return step;
    }

    // 设置执行监听器
    @Bean
    public JobExecutionListener listener() {
        return new JobExecutionListenerSupport() {
            @Override
            public void afterJob(JobExecution jobExecution) {
                System.out.println("批处理执行后： " + jobExecution.getStatus() + " - " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }

            @Override
            public void beforeJob(JobExecution jobExecution) {
                System.out.println("批处理执行前： " + jobExecution.getStatus() + " - " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }
        };
    }
}
