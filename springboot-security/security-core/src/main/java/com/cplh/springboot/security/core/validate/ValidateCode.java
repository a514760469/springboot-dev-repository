package com.cplh.springboot.security.core.validate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 封装验证码对象
 */
public class ValidateCode implements Serializable {


    private static final long serialVersionUID = 2717547100641041363L;

    private String code;// 存到session

    private LocalDateTime expireTime;// 过期时间

    /**
     * 是否过期
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public ValidateCode( String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

}
