package com.cplh.springboot.security.core.properties;

public class SmsCodeProperties {

    private int length = 6;

    private int expireIn = 60;

    private String url = ""; // 验证码需要拦截的url

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
