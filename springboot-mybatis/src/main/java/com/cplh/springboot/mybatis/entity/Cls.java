package com.cplh.springboot.mybatis.entity;

import lombok.Data;

import java.util.List;

@Data
public class Cls {

    private Integer id;

    private String cname;

    private Integer total;

    private List<Student> students;

}