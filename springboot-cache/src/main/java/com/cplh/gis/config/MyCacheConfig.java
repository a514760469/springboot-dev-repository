package com.cplh.gis.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCacheConfig {

	/**
	 * 缓存key生成器
	 * @return
	 */
	@Bean("myKeyGenerator")
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				return method.getName() + "[" + Arrays.asList(params).toString() + "]";
			}
		};
	}
}
