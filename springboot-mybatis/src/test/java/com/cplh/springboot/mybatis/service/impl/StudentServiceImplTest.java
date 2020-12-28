package com.cplh.springboot.mybatis.service.impl;

import com.cplh.springboot.mybatis.entity.Student;
import com.cplh.springboot.mybatis.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceImplTest {

    @Autowired
    StudentService studentService;


    @Test
    public void insertStudent() {
        Student student = new Student();
        student.setName("测试1");
        student.setAddress("111");
        student.setAge(21);
        student.setSex("1");
        Student save = studentService.insertStudent(student);
        System.err.println(save);
    }

    @Test
    public void selectByPrimaryKey() {
        Student student = studentService.selectByPrimaryKey(8);
        System.out.println(student);
    }

    @Test
    public void findPage() {
        PageInfo<Student> page = studentService.findPage();
        System.out.println(page);
    }

    @Test
    public void update() {
        Student student = new Student();
        student.setName("update");
        student.setId(8);
        studentService.updateById(student);
    }
}