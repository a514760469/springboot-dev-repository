package com.cplh.springboot.mybatis.dao;

import com.cplh.springboot.mybatis.entity.Cls;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClsMapperTest {

    @Autowired
    ClsMapper clsMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        Cls cls = clsMapper.selectByPrimaryKey(1);
        System.out.println(cls.getStudents());
    }

    @Test
    public void testSelectClsAndStudent() {
        Cls cls = clsMapper.selectClsAndStudent(1);
        System.err.println(cls.getStudents().size());
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}