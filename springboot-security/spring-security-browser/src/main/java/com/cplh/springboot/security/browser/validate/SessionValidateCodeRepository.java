package com.cplh.springboot.security.browser.validate;

import com.cplh.springboot.security.core.validate.ValidateCode;
import com.cplh.springboot.security.core.validate.ValidateCodeRepository;
import com.cplh.springboot.security.core.validate.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 浏览器使用Session存验证码
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /**
     * 验证码放入 Session 中的前缀
     */
    private String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest webRequest, ValidateCode code, ValidateCodeType type) {
        sessionStrategy.setAttribute(webRequest, getSessionKey(webRequest, type), code);
    }

    @Override
    public ValidateCode get(ServletWebRequest webRequest, ValidateCodeType type) {
        return (ValidateCode) sessionStrategy.getAttribute(webRequest, getSessionKey(webRequest, type));
    }

    @Override
    public void remove(ServletWebRequest webRequest, ValidateCodeType type) {
        sessionStrategy.removeAttribute(webRequest, getSessionKey(webRequest, type));
    }

    /**
     * 构建验证码的key
     * @param webRequest
     * @param type
     * @return
     */
    private String getSessionKey(ServletWebRequest webRequest, ValidateCodeType type) {
        return SESSION_KEY_PREFIX + type.toString().toUpperCase();
    }
}
