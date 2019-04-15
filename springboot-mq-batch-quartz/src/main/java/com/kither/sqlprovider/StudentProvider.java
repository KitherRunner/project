package com.kither.sqlprovider;

import com.kither.bean.Student;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class StudentProvider {

    public String findAll() {
        return new SQL() {{
            SELECT("id", "name", "gender", "comment");
            FROM("t_bd_student");
        }}.toString();
    }

    /**
     * 查询指定student
     *
     * @param id
     * @return
     */
    public String find(final Integer id) {
        return new SQL() {{
            SELECT("id", "name", "gender", "comment");
            FROM("t_bd_student");
            if (id == null) {
                WHERE("id = 1");
            } else {
                WHERE("id = #{id}");
            }
        }}.toString();
    }

    public String add(final Student student) {
        return new SQL() {{
            INSERT_INTO("t_bd_student");
            if (!StringUtils.isEmpty(student.getName())) {
                VALUES("name", "#{name}");
            }
            if (!StringUtils.isEmpty(student.getGender())) {
                VALUES("gender", "#{gender}");
            }
            if (!StringUtils.isEmpty(student.getComment())) {
                VALUES("comment", "#{comment}");
            }
        }}.toString();
    }
}
