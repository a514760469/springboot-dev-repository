package com.gds.controller;

import com.gds.dao.DepartmentDao;
import com.gds.dao.EmployeeDao;
import com.gds.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);
        return  "emp/list";
    }
    // 去添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        model.addAttribute("depts", departmentDao.getDepartments());
        return "emp/add";
    }
    // 员工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    // 去修改页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable Integer id, Model model) {
        model.addAttribute("emp", employeeDao.get(id));
        model.addAttribute("depts", departmentDao.getDepartments());
        // add 是修改添加2合1页面
        return "emp/add";
    }
    // 修改
    @PutMapping("/emp")
    public String updateEmp(Employee employee) {
        System.out.println("employee: " + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    // 删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
