package com.cplh.springboot.security.core.social.qq.connect;

import com.cplh.springboot.security.core.social.qq.api.QQ;
import com.cplh.springboot.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * oauth完成前5步，并且最后调用getApi完成第连步
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private final String appId;

    /**
     * 第1步引导用户跳转认证服务器的地址
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 第4步获取token返回的地址
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     * 使用自己实现的QQOAuth2Template  放弃OAuth2Template
     * @param appId 应用级别，系统配置
     * @param appSecret 应用级别，系统配置
     */
    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }


}
