package com.cplh.gis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplh.gis.entity.Department;
import com.cplh.gis.service.DeptService;

@RestController
public class DeptController {

	@Autowired
	DeptService deptService;
	
	@GetMapping("/dept/{id}")
	public Department getDeptById(@PathVariable Integer id) {
		return deptService.getDeptById(id);
	}
	
}
