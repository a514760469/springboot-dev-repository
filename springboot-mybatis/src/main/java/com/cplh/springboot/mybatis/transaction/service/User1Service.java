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

    //--------------------------- Propagation.REQUIRES_NEW -----------------------------

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User1 user1) {
        user1Mapper.insert(user1);
    }

    // --------------------------- Propagation.NESTED ---------------------------------

    @Transactional(propagation = Propagation.NESTED)
    public void addNested(User1 user1) {
        user1Mapper.insert(user1);
    }


    // --------------------------- 其他传播方式（用的比较少） ---------------------------------

    /**
     * NEVER 以非事务方式执行，如果事务存在抛出异常
     */
    @Transactional(propagation = Propagation.NEVER)
    public void addNever(User1 user1) {
        user1Mapper.insert(user1);
    }

    /**
     * NOT_SUPPORTED 以非事务方式执行，如果事务存在，挂起当前事务
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupport(User1 user1) {
        user1Mapper.insert(user1);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupportException(User1 user1) {
        user1Mapper.insert(user1);
        throw new RuntimeException("User1Service.addNotSupportException 异常");
    }

    /**
     * Propagation.SUPPORTS 如果有事务就支持，如果没有就以非事务方式执行
     */

    @Transactional(propagation = Propagation.SUPPORTS)
    public void addSupportsException(User1 user1) {
        user1Mapper.insert(user1);
        throw new RuntimeException("User1Service.addSupportsException 异常");
    }

    /**
     * Propagation.MANDATORY 支持当前事务，如果事务不存在抛出异常
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void addMandatory(User1 user1) {
        user1Mapper.insert(user1);
    }



}
