package com.cplh.springboot.security.core.validate.impl;

import com.cplh.springboot.security.core.validate.*;
import com.cplh.springboot.security.core.validate.image.ImageCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * ValidateCodeProcessor 接口的抽象实现
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode>
        implements ValidateCodeProcessor {

    /**
     * 依赖查找
     * spring启动时查找所有 {@link ValidateCodeGenerator} 接口的实现。以bean的名称为key放到map中
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;


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

    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest webRequest) {

        ValidateCodeType validateCodeType = getValidateCodeType(webRequest);
//        String sessionKey = getSessionKey(webRequest);

        // Session 中的验证码
//        C codeInSession = (C) sessionStrategy.getAttribute(webRequest, sessionKey);
        C codeInSession = (C) validateCodeRepository.get(webRequest, validateCodeType);
        // request 中的验证码
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(webRequest.getRequest(),
                    validateCodeType.getParamNameOnValidate());

        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(codeInSession.isExpired()){
//            sessionStrategy.removeAttribute(webRequest, sessionKey);
            validateCodeRepository.remove(webRequest, validateCodeType);
            throw new ValidateCodeException("验证码已过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        validateCodeRepository.remove(webRequest, validateCodeType);
//        sessionStrategy.removeAttribute(webRequest, sessionKey);
    }

    /**
     * 保存校验码
     * sessionStrategy
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType(request));
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
     * 根据请求的url获取校验码的类型     /code/的后半段  sms 或 image
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    /**
     * 根据请求的url获取校验码的类型
     * @param webRequest
     * @return ValidateCodeType枚举类
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest webRequest) {
        // 截取当前类 CodeProcessor 之前的名称
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 发送校验码，由子类实现
     * @param webRequest
     * @param validateCode
     */
    protected abstract void send(ServletWebRequest webRequest, C validateCode) throws Exception;

}
