package com.cplh.springboot.security.core.support;

import lombok.Data;

/**
 * 社交登录的一些信息。
 * 可以让用户登录或注册时显示友好的信息
 */
@Data
public class SocialUserInfo {

    private String providerId;// 哪一个第三方在做社交登录

    private String providerUserId;// openid

    private String nickname;// 昵称

    private String headImg;// 头像

}
