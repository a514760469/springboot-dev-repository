package com.cplh.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 用于免注册登录，如果不注册这个bean，则不实现免注册登录功能
 */
//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        // 根据社交用户登录信息默认创建用户并返回用户唯一标识, 真实场景根据业务添加
        return connection.getDisplayName();
    }
}
