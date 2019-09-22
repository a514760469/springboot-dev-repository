package com.cplh.springboot.security.core.properties;

/**
 * Social 的相关配置
 */
public class SocialProperties {

    private QQProperties qq = new QQProperties();

    private String filterProcessesUrl = "/auth";

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
