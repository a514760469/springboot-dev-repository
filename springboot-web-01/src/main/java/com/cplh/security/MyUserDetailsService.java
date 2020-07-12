package com.cplh.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 这个类应该在Demo项目里
 */
@Component("userDetailsService")
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired
    // 注入 DAO

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名：" + username);
        return buildUser(username);
    }

    /**
     * SocialUserDetailsService 提供 用于社交登录
     * 根据 userId 构建UserDetails
     * @throws UsernameNotFoundException usernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录用户id：" + userId);
        return buildUser(userId);
    }


    private SocialUserDetails buildUser(String userId) {
        // 使用DAO获取数据库密码  模拟操作
        String encodePassword = passwordEncoder.encode("123456");
        logger.info("数据库密码是：{}", encodePassword);
        return new SocialUser(userId, encodePassword,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
