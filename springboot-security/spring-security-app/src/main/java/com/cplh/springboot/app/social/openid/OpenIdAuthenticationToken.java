package com.cplh.springboot.app.social.openid;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * token 封装 openId 的登录信息
 */
public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;

    private String providerId;

    /**
     *
     * @param openId
     * @param providerId
     */
    public OpenIdAuthenticationToken(String openId, String providerId) {
        super(null);
        this.principal = openId;
        this.providerId = providerId;
        setAuthenticated(false);
    }

    public OpenIdAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getProviderId() {
        return providerId;
    }

}
