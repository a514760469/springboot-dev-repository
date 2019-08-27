package com.cplh.springboot.mybatis.dao;

import com.cplh.springboot.mybatis.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}