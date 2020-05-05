package com.cplh.springboot.mybatis.transaction.service;

import com.cplh.springboot.mybatis.transaction.bean.User2;
import com.cplh.springboot.mybatis.transaction.mapper.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User2Service {

    @Autowired
    private User2Mapper user2Mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User2 user2) {
        user2Mapper.insert(user2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(User2 user){
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

}
