package com.kither.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "User", description = "用户类")
public class User implements Serializable {
    private static final long serialVersionUID = -2201072663014427784L;
    @ApiModelProperty(value = "编号", name = "id", dataType = "string", example = "1")
    private Integer id;
    @ApiModelProperty(value = "名称", name = "name", dataType = "string", example = "张三")
    private String name;
    @ApiModelProperty(value = "性别", name = "gender", dataType = "string", example = "1")
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
