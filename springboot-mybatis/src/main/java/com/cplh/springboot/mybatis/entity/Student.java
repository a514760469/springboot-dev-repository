package com.cplh.springboot.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

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

}