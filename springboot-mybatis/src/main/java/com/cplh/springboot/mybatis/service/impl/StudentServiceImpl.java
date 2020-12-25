package com.cplh.springboot.mybatis.service.impl;

import com.cplh.springboot.mybatis.dao.StudentMapper;
import com.cplh.springboot.mybatis.entity.Student;
import com.cplh.springboot.mybatis.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student insertStudent(Student student) {
        studentMapper.insert(student);
        return student;
    }

    @Override
    public Student selectByPrimaryKey(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Student> findPage() {
        PageHelper.startPage(1, 10);
        List<Student> students = studentMapper.selectList();
        return PageInfo.of(students);
    }

}
