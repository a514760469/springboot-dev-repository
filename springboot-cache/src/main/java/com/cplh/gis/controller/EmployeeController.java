package com.cplh.gis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplh.gis.entity.Employee;
import com.cplh.gis.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@GetMapping("/emp/{id}")
	public Employee getEmployee(@PathVariable Integer id) {
		return employeeService.getEmployee(id);
	}

	@GetMapping("/emp")
	public Employee update(Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	@GetMapping("/delemp")
	public String deleteEmp(Integer id) {
		employeeService.deleteEmployee(id);
		return "success";
	}
	
	@GetMapping("/emp/lastName/{lastName}")
	public Employee getEmpByLastName(@PathVariable String lastName){
		return employeeService.getEmpByLastName(lastName);
	}
	
}
