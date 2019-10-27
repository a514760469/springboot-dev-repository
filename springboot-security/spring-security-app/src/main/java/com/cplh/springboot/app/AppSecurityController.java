package com.cplh.springboot.app;

import com.cplh.springboot.app.social.AppSignUpUtils;
import com.cplh.springboot.security.core.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AppSecurityController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;

    @GetMapping("/social/signUp")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        // 从Session里拿connection
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        socialUserInfo.setProviderId(connection.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        socialUserInfo.setHeadImg(connection.getImageUrl());
        socialUserInfo.setNickname(connection.getDisplayName());

        appSignUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());

        return socialUserInfo;
    }

}
