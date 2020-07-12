package com.cplh.springboot.security.core.validate.sms;

import com.cplh.springboot.security.core.properties.SecurityProperties;
import com.cplh.springboot.security.core.validate.ValidateCode;
import com.cplh.springboot.security.core.validate.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

}
