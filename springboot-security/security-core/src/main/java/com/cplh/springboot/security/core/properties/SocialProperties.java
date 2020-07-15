package com.cplh.springboot.security.core.properties;

import lombok.Data;

/**
 * Social 的相关配置
 */
@Data
public class SocialProperties {

    private QQProperties qq = new QQProperties();

    private String filterProcessesUrl = "/auth";

}
