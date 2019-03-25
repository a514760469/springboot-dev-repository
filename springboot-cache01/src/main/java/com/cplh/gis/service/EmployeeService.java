package com.cplh.gis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.cplh.gis.entity.Employee;
import com.cplh.gis.mapper.EmployeeMapper;

@CacheConfig(cacheNames = "emp")// 抽取缓存的公共配置
@Service
public class EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;
	/**
	 * 将方法的运行结果缓存
	 * 几个属性：
	 * 	1、cacheNames/value 指定缓存名字: 可以多个(数组)
	 * 	2、key 缓存数据时使用的key：可以用它来指定， 默认使用方法参数的值
	 * 		SpEl：#id: 参数id的值
	 * 	3、keyGenerator key生成器：可以指定key生成器
	 * 		key/keyGenerator 2选1
	 * 	4、cacheManager 指定从哪个缓存管理器取值； cacheResolver 缓存解析器 2选1
	 * 	5、condition 指定符合条件的情况下才缓存
	 * 		condition = "#id>0"
	 * 	6、unless 否定缓存，和condition相反
	 * 		unless = "#result == null"
	 *  7、	sync 	是否使用异步模式
	 *  
	 *  原理
	 *  springBoot缓存自动配置CacheAutoConfiguration
	 *  org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration	
	 *  org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
	 *  org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
	 *  org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
	 *  org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
	 *  org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
	 *  org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
	 *  org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
	 *  org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
	 *  org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
	 *  
	 *  默认SimpleCacheConfiguration生效
	 *  	给容器中注册ConcurrentMapCacheManager
	 *  运行流程 @Cacheable 
	 *  	1、方法运行之前先去查询cache（缓存组件） 按照cacheNames指定的名字获取
	 *  		（CacheManager先获取相应的缓存）第一次获取缓存如果没有 会自动创建出来
	 *  			getCache(cacheNames);
	 *  	2、去cache中查找缓存，使用一个key， 默认就是方法参数
	 *  		key是按照某种策略生成的，KeyGenerator、默认SimpleKeyGenerator
	 *  		如果没有参数：key = new SimpleKey();
	 *  		如果有一个参数：key = 参数值;
	 *  		如果多个有参数：key = new SimpleKey(params);
	 *  	3、没有查到缓存就调用目标方法
	 *  	4、将目标方法返回值 放进缓存中
	 *  执行之前先检查缓存，如果没有运行方法。cacheName 方法名，key 参数值
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames = {"emp"}/*, keyGenerator = "myKeyGenerator", condition = "#a0>1", unless = "#a0==2"*/)
	public Employee getEmployee(Integer id) {
		System.out.println("查询:"+ id);
		return employeeMapper.getEmpById(id);
	}
	
	/**
	 * @CachePut 修改数据库的某个数据，同时更新缓存
	 * 1、先调用目标方法
	 * 2、将目标方法结果缓存
	 * 
	 * 更新用的Key要一致 如果不指定，默认key为参数 employee, value 为返回值employee
	 * key = "#employee.id"
	 */
	@CachePut(value = "emp", key = "#result.id")
	public Employee updateEmployee(Employee employee) {
		System.out.println("updateEmp:" + employee);
		employeeMapper.updateEmp(employee);
		return employee;
	}
	/**
	 * @CacheEvict 清除缓存
	 * 1、key要清楚的key, 默认参数值
	 * 2、allEntries = true 清除这个缓存中所有的数据
	 * 3、beforeInvocation = false 在方法调用之后执行（默认）：如果方法出现异常，缓存不会清除
	 * 		
	 * @param id
	 */
	@CacheEvict(value = "emp", key = "#id", allEntries = true)
	public void deleteEmployee(Integer id) {
		System.out.println("delete: " + id);
//		employeeMapper.deleteEmpById(id);
	}
	
	/**
	 * @Caching 定义复杂的缓存规则
	 * @param lastName
	 * @return
	 */
	@Caching(
			cacheable = {@Cacheable(value = "emp", key = "#lastName")},
			put = {
					@CachePut(value = "emp", key = "#result.id"),
					@CachePut(value = "emp", key = "#result.email")
					}
	)
	public Employee getEmpByLastName(String lastName) {
		return employeeMapper.getEmyByLastName(lastName);
	}
	
	
	
}



