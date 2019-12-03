package com.gds.springboot.rabbitmq.rabbit.rpc;

import org.springframework.core.SpringVersion;

import java.io.Serializable;

/**
 * @author zhanglifeng
 * @date 2019/12/3/0003
 */
public class SendStatus implements Serializable {

    private static final String serialVersionUID = SpringVersion.getVersion();

    private String phone;

    private String result;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
