package com.cplh.gis.user.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * @author zhanglifeng
 * @since 2020-09-24 18:01
 */
@Activate
public class MyRpcFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("invocation: " +invocation);
        Result result = invoker.invoke(invocation);
        System.out.println(result);
        return result;
    }
}
