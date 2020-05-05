package com.cplh.springboot.mybatis.transaction.service;

import com.cplh.springboot.mybatis.transaction.bean.User1;
import com.cplh.springboot.mybatis.transaction.bean.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试事务外围方法
 */
@Service
public class TransactionPropagationExample {

    @Autowired
    User1Service user1Service;

    @Autowired
    User2Service user2Service;

    /**
     * 外围方法没有开启事务
     * 张三 李四插入成功，user1Service user2Service 在自己的事务中独立运行，
     * 外围异常不影响
     */
    public void noTransactionExceptionRequiredRequired() {
        User1 user1 = new User1("张三");
        user1Service.addRequired(user1);


        User2 user2 = new User2("李四");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }

    /**
     * 张三 插入，李四未插入， 李四插入抛出异常只回滚李四
     */
    public void noTransactionRequiredRequiredException() {
        User1 user1 = new User1("张三");
        user1Service.addRequired(user1);


        User2 user2 = new User2("李四");
        user2Service.addRequiredException(user2);
    }

}
