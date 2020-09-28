package com.cplh.gis.user.rpc;

/**
 * @author zhanglifeng
 * @since 2020-08-31 15:55
 */
public class RpcTestServiceImpl implements RpcTestService {

    @Override
    public String hello(String name) {
        return "Yo man Hello，I am" + name;
    }
}
