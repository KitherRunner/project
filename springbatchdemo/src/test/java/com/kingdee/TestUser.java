package com.kingdee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
// 这里不能再次加载db.properties,不然报错：Line 1 in XML document from class path resource [db.properties] is invalid;
@ContextConfiguration({"classpath:spring/*.xml"})
// 在跑单元测试的时候真实的启一个web服务，然后开始调用Controller的Rest API，待单元测试跑完之后再将web服务停掉
@WebAppConfiguration
public class TestUser {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFindAll() throws Exception {
        // users前面必须加 / ，不然报错找不到对应的映射
        MvcResult students = mockMvc.perform(get("/users")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        students.getFlashMap().forEach((key, user) -> System.out.println(user));
    }

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @Test
    public void testBatch() throws Exception {
        JobParameter jobParameter = new JobParameter(new Date());
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("date", jobParameter);
        JobExecution execution = jobLauncher.run(job, new JobParameters(jobParameterMap));
        System.out.println("status: " + execution.getStatus());
    }
}
