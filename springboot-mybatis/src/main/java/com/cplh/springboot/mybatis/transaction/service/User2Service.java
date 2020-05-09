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

    /**
     * 开启 Propagation.REQUIRED  抛出RuntimeException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(User2 user){
        user2Mapper.insert(user);
        throw new RuntimeException("User2Service.addRequiredException异常");
    }

    /**
     * 内部捕获异常，让外围方法感知不到
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredExceptionAndTry(User2 user){
        user2Mapper.insert(user);
        try {
            throw new RuntimeException("User2Service.addRequiredException异常");
        } catch (RuntimeException e) {
            System.err.println("User2Service.addRequiredExceptionAndTry异常，被捕获");
        }
    }

    //------------------------ Propagation.REQUIRES_NEW ---------------------

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User2 user2) {
        user2Mapper.insert(user2);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewException(User2 user){
        user2Mapper.insert(user);
        throw new RuntimeException("User2Service.addRequiresNewException异常");
    }

}
