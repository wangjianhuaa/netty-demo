package com.demo.netty.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @author wangjianhua
 * @Description 用户实体 用于保存到es中
 * @date 2021/8/10 11:19
 */
@Document(indexName = "demo",type = "group_user")
public class User {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String level;

    private Date entryDate;

    private String mobile;

    private String email;

    private String address;

    public User(String id, String name, Integer age, String level, Date entryDate, String mobile, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.level = level;
        this.entryDate = entryDate;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
