package com.cplh.springboot.security.core.validate.sms;

public class DefaultSmsCodeSender implements SmsCodeSender {

    /**
     * 发送方法，调用短信接口发送
     * @param mobile
     * @param code
     */
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码：" + code);
    }

}
