package com.cplh.springboot.security.core.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 * AuthenticationException 是 springSecurity所有认证异常的父异常
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
