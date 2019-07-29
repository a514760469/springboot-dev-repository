package com.cplh.springboot.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired
    // 注入 DAO

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("登录用户名：" + username);

        // 使用DAO获取数据库密码  模拟操作
        String encodePassword = passwordEncoder.encode("123456");
        logger.info("数据库密码是：{}", encodePassword);

        User user = new User(username, encodePassword,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return user;
    }

}
