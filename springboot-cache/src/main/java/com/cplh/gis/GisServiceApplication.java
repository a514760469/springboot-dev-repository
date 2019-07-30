package com.cplh.gis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
/**
 * @EnableCaching 启动spring缓存抽象
 * @author zhanglifeng
 * 
 * 整合Redis作为缓存
 * 1、引入starter
 * 2、配置redis
 * 3、测试缓存
 * 
 * 	RedisCacheManager生效
 * 默认k-v都是Object  利用jdk序列化规则，如何使用json保存
 * 自定义RedisCacheManager
 * 
 */
@MapperScan("com.cplh.gis.mapper")
@SpringBootApplication
@EnableCaching
public class GisServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GisServiceApplication.class, args);
	}
}
