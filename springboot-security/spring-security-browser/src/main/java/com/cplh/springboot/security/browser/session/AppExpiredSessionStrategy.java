package com.cplh.springboot.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * session失效 并发
 * ConcurrentSessionFilter
 */
public class AppExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public AppExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
//        event.getResponse().setContentType("application/json;charset=utf-8");
//        event.getResponse().getWriter().write("并发登录！");
//        logger.info("并发登录, getRequestURL: " + event.getRequest().getRequestURL());
        onSessionInvalid(event.getRequest(), event.getResponse());
    }


    @Override
    protected boolean isConcurrency() {
        return true;
    }


}
