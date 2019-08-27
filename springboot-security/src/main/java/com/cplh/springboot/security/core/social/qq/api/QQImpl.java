package com.cplh.springboot.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 获取openId 的url
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    // 获取用户信息的url
    private static final String URL_GET_USERINFO =
            "https://graph.qq.com/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private String accessToken;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.accessToken = accessToken;
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        logger.info("获取用户openid的结果: \n {}", result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo()  {
        String url = String.format(URL_GET_USERINFO, this.accessToken, appId, openId);
        String jsonResult = getRestTemplate().getForObject(url, String.class);
        logger.info("获取用户QQUserInfo的结果: \n {}", jsonResult);

        try {
            QQUserInfo userInfo = objectMapper.readValue(jsonResult, QQUserInfo.class);
            userInfo.setOpenId(this.openId);
            return userInfo;
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败", e);
        }

    }

}
