package com.cplh.springboot.security.core.validate;

import com.cplh.springboot.security.core.properties.constant.SecurityConstants;
import com.cplh.springboot.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 验证码过滤器
 * 抽象所有验证的逻辑
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 校验失败处理器
     */
    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 存放所有需要校验验证码的url
     * key: url  value: 哪种验证的类型
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 初始化
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
    }

    /**
     * 系统中配置的需要校验验证码的URL根据校验的类型放入map
     */
    private void addUrlToMap(String urlStr, ValidateCodeType validateCodeType) {
        if (StringUtils.isNotBlank(urlStr)) {
            // split(",")
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlStr, ",");
            for (String url : urls) {
                urlMap.put(url, validateCodeType);
            }
        }

    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "GET")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }

    /**
     * 与doFilter相同的契约，但保证仅在单个请求线程内对每个请求调用一次
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeType type = getValidateCodeType(request);

        if (type != null) {
            logger.info("校验请求：( " + request.getRequestURI() + " ) 中的验证码" );
            logger.info("验证码类型：" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));

                logger.info("验证码校验通过.");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}
