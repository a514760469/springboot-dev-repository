package com.cplh.springboot.security.core.properties;

import lombok.Data;

/**
 * springboot 1.5.x 这个类继承SocialProperties
 * springboot 2.x 没有social包了 -_-
 */
@Data
public class QQProperties {

    private String appId;

    private String appSecret;
    /**
     * 默认就是qq
     */
    private String providerId = "qq";

}
