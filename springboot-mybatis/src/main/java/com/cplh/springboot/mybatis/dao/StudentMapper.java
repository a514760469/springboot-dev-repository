package com.cplh.springboot.mybatis.dao;

import com.cplh.springboot.mybatis.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    List<Student> selectList();

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    int updateName(@Param("name") String name, @Param("id") Integer id);
}