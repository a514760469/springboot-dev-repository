package com.cplh.springboot.mybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 开启二级缓存需要实现序列化
 */
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -1838160619542008115L;

    private Integer id;

    private String name;

    private Integer age;

    private String sex;

    private String address;

    private Integer cid;

    /* 新增字段 */
    private Date updateTime;

    private String updater;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}