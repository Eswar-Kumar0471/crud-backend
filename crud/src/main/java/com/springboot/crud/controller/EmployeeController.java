package com.springboot.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.model.Employee;
import com.springboot.crud.repo.EmployeeRepository;

@RestController
@RequestMapping("/api/emp-mgt/")
public class EmployeeController {

	@Autowired
	EmployeeRepository repo;
	
	
	//get ALL employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}
}
