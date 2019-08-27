package com.cplh.springboot.mybatis.dao;

import com.cplh.springboot.mybatis.entity.Cls;
import org.springframework.stereotype.Repository;

@Repository
public interface ClsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Cls record);

    int insertSelective(Cls record);

    Cls selectByPrimaryKey(Integer id);

    Cls selectClsAndStudent(Integer id);

    int updateByPrimaryKeySelective(Cls record);

    int updateByPrimaryKey(Cls record);

}