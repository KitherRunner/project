package com.kither.mapper;

import com.kither.pojo.User;
import com.kither.sqlprovider.StudentProvider;
import com.kither.sqlprovider.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @SelectProvider(type = UserProvider.class, method = "findAll")
    List<User> findAll();

    @SelectProvider(type = StudentProvider.class, method = "find")
    User find(Integer id);

    @InsertProvider(type = UserProvider.class, method = "add")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void add(User user);
}
