package com.cplh.springboot.mybatis.transaction.mapper;

import com.cplh.springboot.mybatis.transaction.bean.User1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface User1Mapper {

    @Insert("insert into user1(`name`) values(#{name})")
    int insert(User1 user);

    User1 selectByPrimaryKey(Integer id);
}
