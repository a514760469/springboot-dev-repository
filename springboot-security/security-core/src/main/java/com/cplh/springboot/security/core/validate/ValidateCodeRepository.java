package com.cplh.springboot.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @param webRequest
     * @param code
     * @param type
     */
    void save(ServletWebRequest webRequest, ValidateCode code, ValidateCodeType type);

    /**
     * 获取验证码
     * @param webRequest
     * @param type
     * @return
     */
    ValidateCode get(ServletWebRequest webRequest, ValidateCodeType type);

    /**
     * 移除验证码
     * @param webRequest
     * @param type
     */
    void remove(ServletWebRequest webRequest, ValidateCodeType type);
}
