package com.cplh.springboot.mybatis.service;

import com.cplh.springboot.mybatis.entity.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {

    Student insertStudent(Student student);


    Student selectByPrimaryKey(Integer id);

    /**
     * 基于page helper的分页
     * @return
     */
    PageInfo<Student> findPage();

}
