package com.cplh.springboot.app.validate;

import com.cplh.springboot.security.core.validate.ValidateCode;
import com.cplh.springboot.security.core.validate.ValidateCodeException;
import com.cplh.springboot.security.core.validate.ValidateCodeRepository;
import com.cplh.springboot.security.core.validate.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * app模块使用redis存放验证码
 * 浏览器的实现SessionValidateCodeRepository
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest webRequest, ValidateCode code, ValidateCodeType type) {
        redisTemplate.opsForValue().set(getRedisKey(webRequest, type), code, 30, TimeUnit.MINUTES);
    }


    @Override
    public ValidateCode get(ServletWebRequest webRequest, ValidateCodeType type) {
        return (ValidateCode) redisTemplate.opsForValue().get(getRedisKey(webRequest, type));
    }

    @Override
    public void remove(ServletWebRequest webRequest, ValidateCodeType type) {
        redisTemplate.delete(getRedisKey(webRequest, type));
    }

    /**
     * 构建验证码的key
     * @param webRequest
     * @param type
     * @return
     */
    private String getRedisKey(ServletWebRequest webRequest, ValidateCodeType type) {
        String deviceId = webRequest.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }

        return "code:" + type.toString() + ":" + deviceId;
    }
}
