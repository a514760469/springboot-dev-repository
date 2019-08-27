package com.cplh.springboot.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 自己写一个配置类，目的是重写postProcess() 方法，修改它默认的filterProcessesUrl
 */
public class SecuritySpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public SecuritySpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
