package com.cplh.springboot.mybatis.transaction.service;

import com.cplh.springboot.mybatis.transaction.bean.User1;
import com.cplh.springboot.mybatis.transaction.mapper.User1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User1Service {

    @Autowired
    private User1Mapper user1Mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User1 user1) {
        user1Mapper.insert(user1);
    }
}
