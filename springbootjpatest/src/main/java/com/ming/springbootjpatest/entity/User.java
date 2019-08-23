package com.ming.springbootjpatest.entity;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "t_userinfo")//数据库表明
public class User {
    @Id//声明id为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//声明自动增长
    @Column(name = "id")//声明数据库对应的字段
    private Integer id;
    @Column(name = "user_name")//声明数据库对应的字段
    //定义字段也是有讲究的，比如首字母小写，后边的驼峰，对应的数据库字段，遇到驼峰用下划线断开
    //比如实体类定义的userName，则数据库字段为user_name，
    //比如实体类定义的username,则数据库字段也为username
    private String userName;
    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
