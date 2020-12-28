package com.cplh.springboot.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Arrays;

/**
 * @author zhanglifeng
 * @since 2020-12-04
 * 注解@Signature指定方法的签名，要拦截的方法
 */
@Component
@Intercepts({
//    @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class}),
//    @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})

})
public class TestMyInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Arrays.stream(args).forEach(System.out::println);
        System.out.println("method：" + invocation.getMethod().getName());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
//        System.err.println(target);
        return Plugin.wrap(target, this);
    }
}
