package com.kither.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    public String findAll() {
        return new SQL() {{
            SELECT("id,name,gender");
            FROM("t_bd_user");
        }}.toString();
    }
}
