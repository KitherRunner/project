package com.kither.mapper;

import com.kither.pojo.Student;
import com.kither.sqlprovider.StudentProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface StudentMapper {

    @SelectProvider(type = StudentProvider.class, method = "findAll")
    List<Student> findAll();

    @SelectProvider(type = StudentProvider.class, method = "find")
    Student find(Integer id);

    @InsertProvider(type = StudentProvider.class, method = "add")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void add(Student student);
}
