package com.cplh.springboot.app.social.openid;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.HashSet;
import java.util.Set;

public class OpenIdAuthenticationProvider implements AuthenticationProvider {


    private SocialUserDetailsService userDetailsService;

    private UsersConnectionRepository usersConnectionRepository;

    /**
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        OpenIdAuthenticationToken token = (OpenIdAuthenticationToken) authentication;

        Set<String> providerUserIds = new HashSet<>();
        providerUserIds.add((String) token.getPrincipal());

        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(token.getProviderId(), providerUserIds);

        if (providerUserIds.isEmpty() || providerUserIds.size() != 1) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        String userId = userIds.iterator().next();

        UserDetails user = userDetailsService.loadUserByUserId(userId);

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        // 重新构造一个认证成功后的token
        OpenIdAuthenticationToken authenticationResultToken = new OpenIdAuthenticationToken(user, user.getAuthorities());

        // 之前在 Filter里设置的信息复制过来
        authenticationResultToken.setDetails(token.getDetails());

        return authenticationResultToken;
    }

    /**
     * 是否使用这个 Provider 来验证，
     * @param authentication authentication 是否是 OpenIdAuthenticationToken 这个类型的
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public SocialUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(SocialUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UsersConnectionRepository getUsersConnectionRepository() {
        return usersConnectionRepository;
    }

    public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository) {
        this.usersConnectionRepository = usersConnectionRepository;
    }
}
