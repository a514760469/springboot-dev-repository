package com.cplh.springboot.security.browser;

import com.cplh.springboot.security.core.support.SimpleResponse;
import com.cplh.springboot.security.core.support.SocialUserInfo;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.properties.constant.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final RequestCache requestCache = new HttpSessionRequestCache();

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    ProviderSignInUtils providerSignInUtils;

    /**
     * 当需要身份认证时，到这里！
     * @return  SimpleResponse
     */
    @RequestMapping(value = {SecurityConstants.DEFAULT_UNAUTHENTICATION_URL})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String target = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是：{}", target);
            // 如果引发跳转的请求是一个html
            if (StringUtils.endsWithIgnoreCase(target, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证, 引导用户去登录页");
    }

    /**
     * 在注册页发这个请求获取用户信息
     * 获取SocialUserInfo 从session里拿
     */
    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        // 从Session里拿connection
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        socialUserInfo.setProviderId(connection.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        socialUserInfo.setHeadImg(connection.getImageUrl());
        socialUserInfo.setNickname(connection.getDisplayName());
        return socialUserInfo;
    }

    /**
     * session失效跳转到这
     */
    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid() {

        String msg = "session失效";
        return new SimpleResponse(msg);
    }

    /**
     * 当前用户信息
     */
    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }
}
