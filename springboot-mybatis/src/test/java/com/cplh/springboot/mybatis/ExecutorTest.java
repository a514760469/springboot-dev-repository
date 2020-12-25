package com.cplh.springboot.mybatis;

import org.apache.ibatis.executor.*;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglifeng
 * @since 2020-12-22
 */
public class ExecutorTest {

    private Configuration configuration;

    private JdbcTransaction jdbcTransaction;

    private MappedStatement ms;

    private SqlSessionFactory factory;

    @Before
    public void init() throws SQLException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(ExecutorTest.class.getResourceAsStream("/mybatis-config.xml"));
        configuration = factory.getConfiguration();

        Connection connection = DriverManager.getConnection("jdbc:mysql:///mb-dev", "root", "123456");
        jdbcTransaction = new JdbcTransaction(connection);
        ms = configuration.getMappedStatement("com.cplh.springboot.mybatis.dao.StudentMapper.selectByPrimaryKey");
    }


    // SimpleExecutor 简单执行器
    @Test
    public void simpleExecutor() throws SQLException {
        SimpleExecutor simpleExecutor = new SimpleExecutor(configuration, jdbcTransaction);

        List<Object> list = simpleExecutor.doQuery(ms, 12, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        simpleExecutor.doQuery(ms, 12, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        System.out.println(list.get(0));
    }

    // ReuseExecutor 可重用执行器
    @Test
    public void reuseExecutor() throws SQLException {
        ReuseExecutor executor = new ReuseExecutor(configuration, jdbcTransaction);

        List<Object> list = executor.doQuery(ms, 12, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        executor.doQuery(ms, 12, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        System.out.println(list.get(0));
    }

    // BatchExecutor 批处理执行器 （写数据）
    @Test
    public void batchExecutor() throws SQLException {
        BatchExecutor executor = new BatchExecutor(configuration, jdbcTransaction);

        MappedStatement ms =
                configuration.getMappedStatement("com.cplh.springboot.mybatis.dao.StudentMapper.updateName");

        Map<String, Object> map = new HashMap<>();
        map.put("name", "哈哈哈哈哈");
        map.put("id", 12);

        executor.doUpdate(ms, map);
        executor.doUpdate(ms, map);
        executor.doFlushStatements(false);
    }


    // BaseExecutor 基础执行器 抽象出来，一级缓存，获取连接
    @Test
    public void baseExecutor() throws SQLException {
        SimpleExecutor executor = new SimpleExecutor(configuration, jdbcTransaction);
        executor.query(ms, 12, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);
        executor.query(ms, 12, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);
    }


    @Test
    public void cacheExecutor() throws SQLException {
        SimpleExecutor simpleExecutor = new SimpleExecutor(configuration, jdbcTransaction);
        // 装饰器模式
        CachingExecutor executor = new CachingExecutor(simpleExecutor);// 二级缓存，缓存相关逻辑，数据库相关逻辑由delegate实现
        executor.query(ms, 12, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);
        executor.commit(true);
        executor.query(ms, 12, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);
        executor.query(ms, 12, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);

    }



    @Test
    public void sessionTest() {
        SqlSession sqlSession = factory.openSession(true);
        List<Object> list = sqlSession.selectList("com.cplh.springboot.mybatis.dao.StudentMapper.selectByPrimaryKey", 12);
        System.out.println(list.get(0));

    }
}
