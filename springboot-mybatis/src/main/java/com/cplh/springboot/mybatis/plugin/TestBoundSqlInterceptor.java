package com.cplh.springboot.mybatis.plugin;

import com.github.pagehelper.BoundSqlInterceptor;
import com.github.pagehelper.util.MetaObjectUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.reflection.MetaObject;

/**
 * MyBatis pageHelper 的sql拦截器
 * @author zhanglifeng
 * @since 2020-12-26
 */
public class TestBoundSqlInterceptor implements BoundSqlInterceptor {
    public static final String COMMENT = "\n /* TestBoundSqlInterceptor */\n";

    @Override
    public BoundSql boundSql(Type type, BoundSql boundSql, CacheKey cacheKey, Chain chain) {
        if (type == Type.ORIGINAL) {
            String sql = boundSql.getSql();
            MetaObject metaObject = MetaObjectUtil.forObject(boundSql);
            metaObject.setValue("sql", sql + COMMENT);
        }
        return chain.doBoundSql(type, boundSql, cacheKey);
    }

}