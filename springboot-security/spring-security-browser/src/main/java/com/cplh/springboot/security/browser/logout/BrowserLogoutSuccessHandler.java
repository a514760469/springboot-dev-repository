package com.cplh.springboot.security.browser.logout;

import com.cplh.springboot.security.core.support.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BrowserLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String signOutUrl;

    private ObjectMapper objectMapper;

    public BrowserLogoutSuccessHandler(String signOutUrl, ObjectMapper objectMapper) {
        this.signOutUrl = signOutUrl;
        this.objectMapper = objectMapper;
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        log.info("退出成功");
        if (StringUtils.isBlank(signOutUrl)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        } else {
            response.sendRedirect(signOutUrl);
        }

    }


}
