package com.kither.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "Student", description = "学生类")
public class Student implements Serializable {
    private static final long serialVersionUID = 4025249461687019320L;
    @ApiModelProperty(value = "编号", name = "id", dataType = "string", example = "1")
    private String id;
    @ApiModelProperty(value = "名称", name = "name", dataType = "string", example = "张三")
    private String name;
    @ApiModelProperty(value = "性别", name = "gender", dataType = "string", example = "1")
    private String gender;
    @ApiModelProperty(value = "说明", name = "comment", dataType = "string", example = "我是张三")
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
