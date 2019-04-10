package com.kither.reader;

import com.kither.pojo.User;
import com.kither.service.UserService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserReader implements ItemReader<User>, InitializingBean {

    private Integer index = 0;

    private Date date;

    private List<User> userList;

    @Autowired
    private UserService userService;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
