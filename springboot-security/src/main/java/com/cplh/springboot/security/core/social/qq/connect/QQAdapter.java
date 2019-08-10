package com.cplh.springboot.security.core.social.qq.connect;

import com.cplh.springboot.security.core.social.qq.api.QQ;
import com.cplh.springboot.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试服务是否可用 这里默认永远可用
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     * 服务提供商提供的个性化用户信息转换标准信息
     * @param api qq提供的UserInfo
     * @param values spring标准信息
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo qqUserInfo = api.getUserInfo();
        values.setDisplayName(qqUserInfo.getNickname());// 名称
        values.setImageUrl(qqUserInfo.getFigureurl_qq_1());// 头像
        values.setProfileUrl(null); // 个人主页，QQ是没有的
        values.setProviderUserId(qqUserInfo.getOpenId());// 服务商的用户id：openId
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    /**
     * 更新个人信息 QQ 没有, 所以不实现
     * @param api
     * @param message
     */
    @Override
    public void updateStatus(QQ api, String message) {

    }

}
