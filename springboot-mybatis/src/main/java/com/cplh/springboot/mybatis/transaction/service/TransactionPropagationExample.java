package com.cplh.springboot.mybatis.transaction.service;

import com.cplh.springboot.mybatis.transaction.bean.User1;
import com.cplh.springboot.mybatis.transaction.bean.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试事务外围方法
 */
@Service
public class TransactionPropagationExample {

    @Autowired
    private User1Service user1Service;

    @Autowired
    private User2Service user2Service;

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
     * 张三插入，李四未插入， 李四插入抛出异常只回滚李四
     * 结论：外围方法没有开启事务 Propagation.REQUIRED 修饰内部方法会开启新事务，相互独立互不干扰
     */
    public void noTransactionRequiredRequiredException() {
        User1 user1 = new User1("张三");
        user1Service.addRequired(user1);


        User2 user2 = new User2("李四");
        user2Service.addRequiredException(user2);
    }

    /**
     * 外围方法开启事务，这个是使用率比较高的场景。
     * 外围方法开启事务
     * 张三 李四均未插入，内部方法 Propagation.REQUIRED 如果有事务支持当前事务，所以没有开启新事务
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionExceptionRequiredRequired() {
        User1 user1 = new User1("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2("李四");
        user2Service.addRequired(user2);
        throw new RuntimeException("外围方法异常");
    }

    /**
     * 张三 李四均未插入，user1Service、user2Service 还是一个事务中
     */
    @Transactional
    public void transactionRequiredRequiredException() {
        User1 user1 = new User1("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2("李四");
        user2Service.addRequiredException(user2);
    }

    /**
     * 张三李四均未插入，外围方法虽catch，但user2Service 依然感知到异常->回滚
     * 结论：Propagation.REQUIRED修饰的内部方法会加入外围方法的事务中，整个事务只要感知到异常就会回滚
     */
    @Transactional
    public void transactionRequiredRequiredExceptionTry() {
        User1 user1 = new User1("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2("李四");
        try {
            user2Service.addRequiredException(user2);
        } catch (RuntimeException e) {
            System.err.println("方法回滚");
        }
    }

    /**
     * 外围不开启事务, 内部Propagation.REQUIRES_NEW
     * 张三李四插入成功
     */
    public void noTransactionExceptionReqNewReqNew() {
        User1 user1 = new User1("张三");
        user1Service.addRequiresNew(user1);

        User2 user2 = new User2("李四");
        user2Service.addRequiresNew(user2);
        throw new RuntimeException("外围方法异常");
    }

    /**
     * 外围不开启事务, 内部Propagation.REQUIRES_NEW
     * 张三插入 李四未插入， 内部方法分别开启自己的事务，李四抛出异常回滚。
     * 结论： Propagation.REQUIRES_NEW 开启独立事务
     */
    public void noTransactionReqNewReqNewException() {
        User1 user1 = new User1("张三");
        user1Service.addRequiresNew(user1);

        User2 user2 = new User2("李四");
        user2Service.addRequiresNewException(user2);
    }

    /**
     * 张三：未插入 李四：插入 王五：插入
     * 张三和外围方法在一个事务，李四王五分别在自己的事务中
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionExceptionRequiredReqNewReqNew() {
        User1 user1 = new User1("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2("李四");
        user2Service.addRequiresNew(user2);

        User2 ww = new User2("王五");
        user2Service.addRequiresNew(ww);
        throw new RuntimeException("外围方法异常");
    }

    /**
     * 张三：未插入 李四：插入 王五：未插入
     *
     * 李四王五在各自独立的事务中，插入王五的异常继续抛出被外围方法感知，外围方法回滚。
     * 张三在外围方法事务中，所以被连累。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredReqNewReqNewException() {
        User1 user1 = new User1("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2("李四");
        user2Service.addRequiresNew(user2);

        User2 ww = new User2("王五");
        user2Service.addRequiresNewException(ww);
    }

    /**
     * zs：插入 ls：插入 ww：未插入
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredReqNewReqNewExceptionTry() {
        User1 user1 = new User1("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2("李四");
        user2Service.addRequiresNew(user2);

        User2 ww = new User2("王五");
        try {
            user2Service.addRequiresNewException(ww);
        } catch (Exception e) {
            System.err.println("方法回滚");
        }
    }

    // ----------------------- Propagation.NESTED -----------------------

    /**
     * 外围方法没有开启事务
     * 张三 李四插入
     */
    public void noTransactionExceptionNestedNested() {
        User1 user1 = new User1("张三");
        user1Service.addNested(user1);

        User2 user2 = new User2("李四");
        user2Service.addNested(user2);

        throw new RuntimeException("外围方法异常");
    }

    /**
     * 张三插入 李四未插入
     * 结论：外围方法未开启事务，Propagation.NESTED和Propagation.REQUIRED作用相同
     */
    public void noTransactionNestedNestedException() {
        User1 user1 = new User1("张三");
        user1Service.addNested(user1);

        User2 user2 = new User2("李四");
        user2Service.addNestedException(user2);
    }

    /**
     * 外围方法开启事务
     * 张三 李四 均未插入
     */
    @Transactional
    public void transactionExceptionNestedNested() {
        User1 user1 = new User1("张三");
        user1Service.addNested(user1);

        User2 user2 = new User2("李四");
        user2Service.addNested(user2);

        throw new RuntimeException("外围方法异常");
    }

    /**
     * 张三 李四 均未插入
     */
    @Transactional
    public void transactionNestedNestedException() {
        User1 user1 = new User1("张三");
        user1Service.addNested(user1);

        User2 user2 = new User2("李四");
        user2Service.addNestedException(user2);
    }

    /**
     * 张三插入 李四未插入
     * 结论：Propagation.NESTED修饰的内部方法属于外部事务的子事务，
     *      主事务回滚，子事务一定回滚，子事务可以单独回滚(不被主事务感知)而不影响外围主事务和其他子事务。
     */
    @Transactional
    public void transactionNestedNestedExceptionTry() {
        User1 user1 = new User1("张三");
        user1Service.addNested(user1);

        User2 user2 = new User2("李四");
        try {
            user2Service.addNestedException(user2);
        } catch (Exception e) {
            System.err.println("方法回滚");
        }
    }

    // ----------------------- 其他传播方式（用的少） -----------------------

    @Transactional
    public void otherPropagation() {
        User1 user1 = new User1("张三");
        user1Service.addNotSupport(user1);
    }

    /**
     * 张三插入，user1Service以非事务方式执行
     */
    @Transactional
    public void transactionNotSupportException() {
        User1 user1 = new User1("张三");
        user1Service.addNotSupportException(user1);
    }

    /**
     * 张三插入，有异常但是user1Service以非事务方式执行
     */
    public void noTransactionSupportsException() {
        User1 user1 = new User1("张三");
        user1Service.addSupportsException(user1);
    }

    /**
     * 张三 插入 外围方法去掉@Transactional 抛出异常
     */
    @Transactional
    public void transactionMandatory() {
        User1 user1 = new User1("张三");
        user1Service.addMandatory(user1);
    }


}
