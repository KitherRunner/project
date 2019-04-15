package com.kither.reader;

import com.kither.bean.User;
import com.kither.service.UserService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("userReader")
// 使用此注解设置值的后绑定，在生产step的时候再去绑定date值
@StepScope
public class UserReader implements ItemReader<User>, InitializingBean {

    // 从jobParameters中获取date数据
    @Value("#{jobParameters['date']}")
    private Date date;

    @Autowired
    private UserService userService;

    private List<User> userList;

    private Integer index = 0;

    @Override
    public void afterPropertiesSet() throws Exception {
        userList = userService.findAll();
    }

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        User user = null;
        if (userList != null) {
            int size = userList.size();
            if (size > 0 && index < size) {
                user = userList.get(index++);
            }
        }
        return user;
    }
}
