package com.cplh.springboot.mybatis.service.impl;

import com.cplh.springboot.mybatis.entity.Student;
import com.cplh.springboot.mybatis.service.StudentService;
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

}