package com.cplh.springboot.security.core.properties;

/**
 * springboot 1.5.x 这个类继承SocialProperties
 * springboot 2.x 没有social包了 -_-
 */
public class QQProperties {

    private String appId;

    private String appSecret;
    /**
     * 默认就是qq
     */
    private String providerId = "qq";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
