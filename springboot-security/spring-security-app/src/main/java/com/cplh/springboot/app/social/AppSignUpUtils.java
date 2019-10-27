package com.cplh.springboot.app.social;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

@Component
public class AppSignUpUtils {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    public void saveConnectionData(WebRequest webRequest, ConnectionData connectionData) {
        redisTemplate.opsForValue().set(getKey(webRequest), connectionData, 10, TimeUnit.MINUTES);
    }

    public void doPostSignUp(WebRequest webRequest, String userId) {
        String key = getKey(webRequest);
        if (BooleanUtils.isFalse(redisTemplate.hasKey(key))) {
            throw new AppSecretException("无法找到缓存的用户社交账号信息");
        }
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);

        if (connectionData == null) {
            throw new AppSecretException("无法找到缓存的用户社交账号信息");
        }
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData
                .getProviderId()).createConnection(connectionData);
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

        redisTemplate.delete(key);
    }

    private String getKey(WebRequest webRequest) {
        String deviceId = webRequest.getParameter("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new AppSecretException("设备id参数不能为空");
        }
        return "app:security:social.connect." + deviceId;
    }

}
