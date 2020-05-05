package com.cplh.springboot.mybatis.transaction.bean;

public class User2 {

    private Integer id;
    private String name;

    public User2() {
    }

    public User2(String name) {
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
