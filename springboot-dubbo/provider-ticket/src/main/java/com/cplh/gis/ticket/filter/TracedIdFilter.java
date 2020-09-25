package com.cplh.gis.ticket.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * @author zhanglifeng
 * @since 2020-09-25 18:57
 */
@Activate(group = Constants.CONSUMER)
public class TracedIdFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = String.valueOf(System.currentTimeMillis());
        RpcContext.getContext().setAttachment("traceId", traceId);
        System.out.println("traceId = " + traceId);

        return invoker.invoke(invocation);
    }
}
