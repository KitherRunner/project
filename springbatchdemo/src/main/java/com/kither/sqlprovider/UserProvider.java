package com.kither.sqlprovider;

import com.kither.pojo.User;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * 构架动态sql
 */
public class UserProvider {

    /**
     * 查询所有user
     *
     * @return
     */
    public String findAll() {
        return new SQL() {{
            SELECT("name", "gender");
            FROM("t_bd_user");
        }}.toString();
    }

    /**
     * 查询指定user
     *
     * @param id
     * @return
     */
    public String find(final Integer id) {
        return new SQL() {{
            SELECT("id", "name", "gender");
            FROM("t_bd_user");
            if (id == null) {
                WHERE("id = 1");
            } else {
                WHERE("id = #{id}");
            }
        }}.toString();
    }

    /**
     * 新增user
     *
     * @param user
     * @return
     */
    public String add(final User user) {
        return new SQL() {{
            INSERT_INTO("t_bd_user");
            if (!StringUtils.isEmpty(user.getName())) {
                VALUES("name", "#{name}");
            }
            if (!StringUtils.isEmpty(user.getGender())) {
                VALUES("gender", "#{gender}");
            }
        }}.toString();
    }
}
