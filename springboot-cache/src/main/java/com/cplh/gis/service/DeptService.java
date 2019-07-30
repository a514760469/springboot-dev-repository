package com.cplh.gis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cplh.gis.entity.Department;
import com.cplh.gis.mapper.DepartmentMapper;

@Service
public class DeptService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Cacheable(cacheNames = "dept")
	public Department getDeptById(Integer id) {
		return departmentMapper.getDeptById(id);
	}
	
}
