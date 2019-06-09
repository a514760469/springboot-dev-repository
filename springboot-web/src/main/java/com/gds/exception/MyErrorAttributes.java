package com.gds.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    // 自己定制返回的错误属性有哪些
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);

        @SuppressWarnings("all")
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", WebRequest.SCOPE_REQUEST);

        map.put("company", "gds");
        map.put("ext", ext);
        return map;
    }
}
