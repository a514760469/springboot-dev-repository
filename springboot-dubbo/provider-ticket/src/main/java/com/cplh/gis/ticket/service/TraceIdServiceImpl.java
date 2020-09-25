package com.cplh.gis.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.cplh.dubbo.api.TraceIdService;

/**
 * @author zhanglifeng
 * @since 2020-09-25 16:43
 */
@Service(timeout = 60000)
public class TraceIdServiceImpl implements TraceIdService {

    @Override
    public void traceIdTest(String key) {
        String traceId = RpcContext.getContext().getAttachment("traceId");
        System.out.println("key = " + key + ", traceId = " + traceId);
    }
}
