package com.cplh.springboot.mybatis.dao;

import com.cplh.springboot.mybatis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    public void testAdd() {
        User user = new User();
        user.setUserName("zhangsan");
        userMapper.insert(user);
    }


}