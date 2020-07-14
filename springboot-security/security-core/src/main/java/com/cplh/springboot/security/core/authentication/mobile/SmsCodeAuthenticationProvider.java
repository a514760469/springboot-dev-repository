package com.cplh.springboot.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * 验证token，如果通过返回认证成功的Authentication
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken token = (SmsCodeAuthenticationToken) authentication;// authentication 实际上就是token
        UserDetails user = userDetailsService.loadUserByUsername((String) token.getPrincipal());// 这里用手机号读取用户信息

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        // 重新构造一个认证成功后的token
        SmsCodeAuthenticationToken authenticationResultToken = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        // 之前在 Filter里设置的信息复制过来
        authenticationResultToken.setDetails(token.getDetails());

        return authenticationResultToken;
    }

    /**
     * 是否使用这个 Provider 来验证，
     * @param authentication authentication 是否是 SmsCodeAuthenticationToken 这个类型的
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
