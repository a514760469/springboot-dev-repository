package com.cplh.springboot.security.core.validate.impl;

import com.cplh.springboot.security.core.validate.ValidateCode;
import com.cplh.springboot.security.core.validate.ValidateCodeGenerator;
import com.cplh.springboot.security.core.validate.ValidateCodeProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * ValidateCodeProcessor 接口的抽象实现
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 依赖查找
     * spring启动时查找所有 {@link ValidateCodeGenerator} 接口的实现。以bean的名称为key放到map中
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;


    /**
     * 主干逻辑
     * 1生成
     * 2保存
     * 3发送
     * @param webRequest Spring的工具类，request和response都可以放这里边
     * @throws Exception
     */
    @Override
    public void create(ServletWebRequest webRequest) throws Exception {
        C validateCode = generate(webRequest);
        save(webRequest, validateCode);
        send(webRequest, validateCode);
    }

    /**
     * 保存校验码
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request,
                SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    }

    /**
     * 生成校验码
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return (C) validateCodeGenerator.generate(request.getRequest());
    }

    /**
     * 根据请求的url获取校验码的类型     /code/的后半段
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    /**
     * 发送校验码，由子类实现
     * @param webRequest
     * @param validateCode
     */
    protected abstract void send(ServletWebRequest webRequest, C validateCode) throws Exception;

}
