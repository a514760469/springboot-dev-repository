package com.cplh.gis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cplh.gis.entity.Employee;
import com.cplh.gis.mapper.EmployeeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GisServiceApplicationTests {

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	RedisTemplate<Object, Employee> empRedisTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void testFind() {
		Employee employee = employeeMapper.getEmpById(1);
		System.out.println(employee);
	}
	
	@Test
	public void contextLoads() {
	}
	/**
	 * redis 
	 * String 字符串、 list 列表、set 集合、 hash 散列、 zset 有序集合
	 * stringRedisTemplate.opsForValue() 字符串
	 * stringRedisTemplate.opsForList() list列表
	 * stringRedisTemplate.opsForSet()
	 */
	@Test
	public void testReids(){
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);
		
	}
	/**
	 * 默认保存对象使用jdk序列化机制，将序列化数据保存到redis
	 * 将数据以json的形式保存
	 * 1、自己将对象保存json
	 * 2、redisTemplate默认的序列化规则
	 */
	@Test
	public void testSaveObj() {
		Employee employee = employeeMapper.getEmpById(1);
		empRedisTemplate.opsForValue().set("emp-1", employee);
	}
	
}
