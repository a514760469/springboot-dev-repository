package com.cplh.springboot.mybatis.entity;

import java.util.List;

public class Cls {

    private Integer id;

    private String cname;

    private Integer total;

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cls{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", total=" + total +
                '}';
    }
}