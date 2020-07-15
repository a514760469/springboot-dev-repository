package com.cplh.springboot.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * AbstractOAuth2ApiBinding实现
 * private final String accessToken;
 * private RestTemplate restTemplate;
 * 此类是多实例的
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    // 获取openId 的url
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    // 获取用户信息userInfo的url
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private final String appId;// oauth_consumer_key：申请QQ登录成功后，分配给应用的appId

    private final String openId;// 用户的id，与QQ号对应

//    private final String accessToken;// 走完oauth2流程后拿到的token

    private final ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);// accessToken作为参数，默认使用header传
//        this.accessToken = accessToken;
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("获取用户openId的结果: \n {}", result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");// 从返回的字符串中截取
    }

    @Override
    public QQUserInfo getUserInfo()  {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String jsonResult = getRestTemplate().getForObject(url, String.class);
        log.info("获取用户QQUserInfo的结果: \n {}", jsonResult);

        try {
            // 最终把返回内容读取对象
            QQUserInfo userInfo = objectMapper.readValue(jsonResult, QQUserInfo.class);
            userInfo.setOpenId(this.openId);
            return userInfo;
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败", e);
        }

    }

}
