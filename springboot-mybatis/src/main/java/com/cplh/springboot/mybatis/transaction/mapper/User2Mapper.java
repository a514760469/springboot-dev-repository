package com.cplh.springboot.mybatis.transaction.mapper;

import com.cplh.springboot.mybatis.transaction.bean.User2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface User2Mapper {

    @Insert("insert into user2(`name`) values(#{name})")
    int insert(User2 user);

    User2 selectByPrimaryKey(Integer id);
}
