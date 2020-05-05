package com.cplh.springboot.mybatis.transaction.bean;

public class User1 {

    private Integer id;
    private String name;

    public User1() {
    }

    public User1(String name) {
        this.name = name;
    }

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
}
