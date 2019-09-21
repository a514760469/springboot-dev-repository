package com.cplh.springboot.security.core.validate.sms;

import com.cplh.springboot.security.core.properties.constant.SecurityConstants;
import com.cplh.springboot.security.core.validate.ValidateCode;
import com.cplh.springboot.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 短信验证码处理器
 * 
 * @author zhailiang
 *
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),
				SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);

		smsCodeSender.send(mobile, validateCode.getCode());
	}

}
