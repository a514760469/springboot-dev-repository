package com.cplh.springboot.security.core.validate.sms;

public interface SmsCodeSender {

    void send(String mobile, String code);

}
