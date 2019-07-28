package com.cplh.springboot.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码处理逻辑
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入 Session 中的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     * @param webRequest Spring的工具类，request和response都可以放这里边
     */
    void create(ServletWebRequest webRequest) throws Exception;

}
