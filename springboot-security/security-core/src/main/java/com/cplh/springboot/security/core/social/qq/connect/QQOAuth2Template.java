package com.cplh.springboot.security.core.social.qq.connect;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * 适用于QQ的 OAuth2Template
 */
public class QQOAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }


    @Override
    protected RestTemplate createRestTemplate() {
        // 父类的创建结果
        RestTemplate restTemplate = super.createRestTemplate();
        // 添加 处理contentType text/html 的Converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        logger.info("获取AccessToken的结果：" + responseStr);
        String[] item = StringUtils.splitByWholeSeparator(responseStr, "&");

        String accessToken = StringUtils.substringAfterLast(item[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(item[1], "="));
        String refreshToken = StringUtils.substringAfterLast(item[2], "=");


        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }
}
