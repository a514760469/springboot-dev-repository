package com.cplh.springboot.security.core.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证码处理器Holder
 */
@Component
public class ValidateCodeProcessorHolder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 依赖查找
     */
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    /**
     * 查找使用哪个验证码处理器
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();

        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            logger.error("验证码处理器: " + name + " 不存在");
            throw new ValidateCodeException("验证码处理器: " + name + " 不存在");
        }
        return processor;
    }

}
