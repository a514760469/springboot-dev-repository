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

    @Test
    public void testNoTransactionException() {
//        transactionPropagationExample.noTransactionExceptionRequiredRequired();
        transactionPropagationExample.noTransactionRequiredRequiredException();
    }
}