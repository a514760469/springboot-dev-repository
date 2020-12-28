package com.cplh.springboot.mybatis;

import com.cplh.springboot.mybatis.entity.Student;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.*;
import org.junit.Before;
import org.junit.Test;

/**
 * 二级缓存测试
 * @author zhanglifeng
 * @since 2020-12-26
 */
public class SecondCacheTest {

    private Configuration configuration;

    private SqlSessionFactory factory;

    @Before
    public void init() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(ExecutorTest.class.getResourceAsStream("/mybatis-config.xml"));
        configuration = factory.getConfiguration();
    }

    @Test
    public void sessionExecutorTest() {
        SqlSession sqlSession = factory.openSession(ExecutorType.REUSE);
    }

    @Test
    public void testCache() {
        Cache cache = configuration.getCache("com.cplh.springboot.mybatis.dao.StudentMapper");// Namespace完整的名称
        cache.putObject("cache", new Student());
        cache.getObject("cache");
    }


    @Test
    public void testCache2() {
        SqlSession session1 = factory.openSession();
        Student s1 = session1.selectOne("com.cplh.springboot.mybatis.dao.StudentMapper.selectByPrimaryKey", 12);
//        Student s = session1.selectOne("com.cplh.springboot.mybatis.dao.StudentMapper.selectByPrimaryKey", 12);
//        System.out.println(s1 == s);
        session1.commit();
        SqlSession session2 = factory.openSession();
        Student s2 = session2.selectOne("com.cplh.springboot.mybatis.dao.StudentMapper.selectByPrimaryKey", 12);
        System.out.println(s1 == s2);

    }


}
