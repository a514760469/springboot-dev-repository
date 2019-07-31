package com.cplh.springboot.mybatis.dao;

import com.cplh.springboot.mybatis.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentMapperTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StudentMapper studentMapper;

    @Test
    public void testStudent() {
        Student student = new Student();
        student.setAddress("aa");
        student.setName("zs");
        studentMapper.insert(student);
    }


    @Test
    public void testAdd() {
    }


}