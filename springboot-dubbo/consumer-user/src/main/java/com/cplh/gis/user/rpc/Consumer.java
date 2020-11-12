package com.cplh.gis.user.rpc;

import static com.cplh.gis.user.rpc.RpcFramework.refer;

/**
 * @author zhanglifeng
 * @since 2020-08-31 16:59
 */
public class Consumer {
    public static void main(String[] args) {

        RpcTestService refer = refer(RpcTestService.class, "127.0.0.1", 2333);
        System.out.println(refer.hello("abc"));
    }
}
