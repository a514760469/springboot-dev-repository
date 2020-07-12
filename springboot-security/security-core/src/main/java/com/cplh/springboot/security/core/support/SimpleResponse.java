package com.cplh.springboot.security.core.support;

import lombok.Data;

/**
 * 封装返回的对象
 */
@Data
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

}
