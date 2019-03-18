package com.kither.pojo;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 4025249461687019320L;
    private String name;
    private String gender;
    private String comment;

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
