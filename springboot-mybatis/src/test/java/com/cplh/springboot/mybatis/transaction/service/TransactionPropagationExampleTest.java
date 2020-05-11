package com.cplh.springboot.mybatis.transaction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionPropagationExampleTest {

    @Autowired
    TransactionPropagationExample transactionPropagationExample;

    /**
     * 测试外围方法不开启事务
     */
    @Test
    public void testNoTransactionException() {
//        transactionPropagationExample.noTransactionExceptionRequiredRequired();
        transactionPropagationExample.noTransactionRequiredRequiredException();
    }

    @Test
    public void testTransactionException() {
        transactionPropagationExample.transactionExceptionRequiredRequired();
    }

    @Test
    public void testTransactionException_2() {
        transactionPropagationExample.transactionRequiredRequiredException();
    }

    @Test
    public void testTransactionException_3() {
        transactionPropagationExample.transactionRequiredRequiredExceptionTry();
    }

    @Test
    public void testNoTransactionRequiresNew() {
        transactionPropagationExample.noTransactionExceptionReqNewReqNew();
    }

    @Test
    public void testNoTransactionRequiresNew_2() {
        transactionPropagationExample.noTransactionReqNewReqNewException();
    }

    @Test
    public void testTransactionExceptionRequiredReqNewReqNew() {
        transactionPropagationExample.transactionExceptionRequiredReqNewReqNew();
    }

    @Test
    public void testTransactionRequiredReqNewReqNewException() {
        transactionPropagationExample.transactionRequiredReqNewReqNewException();
    }

    @Test
    public void testTransactionRequiredReqNewReqNewExceptionTry() {
        transactionPropagationExample.transactionRequiredReqNewReqNewExceptionTry();
    }

    @Test
    public void testNested_1() {
        transactionPropagationExample.noTransactionExceptionNestedNested();
    }

    @Test
    public void testNested_2() {
        transactionPropagationExample.noTransactionNestedNestedException();
    }

    @Test
    public void testNested_3() {
        transactionPropagationExample.transactionExceptionNestedNested();
    }

    @Test
    public void testNested_4() {
        transactionPropagationExample.transactionNestedNestedException();
    }

    @Test
    public void testNested_5() {
        transactionPropagationExample.transactionNestedNestedExceptionTry();
    }




    @Test
    public void testPropagation() {
        transactionPropagationExample.otherPropagation();
    }

    @Test
    public void testTransactionNotSupportException() {
        transactionPropagationExample.transactionNotSupportException();
    }

    @Test
    public void testTransactionSupports() {
        transactionPropagationExample.noTransactionSupportsException();
    }

    @Test
    public void testTransactionMandatory() {
        transactionPropagationExample.transactionMandatory();
    }


}