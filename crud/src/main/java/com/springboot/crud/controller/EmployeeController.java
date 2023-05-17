package com.springboot.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.exception.ResourceNotFoundException;
import com.springboot.crud.model.Employee;
import com.springboot.crud.repo.EmployeeRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/emp-mgt/")
public class EmployeeController {

	@Autowired
	EmployeeRepository emplrepo;
	
	
	//get ALL employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return emplrepo.findAll();
	}
	
	//create employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return emplrepo.save(employee);
	}
	
	//get Employee by ID
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEMployeeById(@PathVariable Long id) {
		Employee employee = emplrepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee Not Exist with id: "+ id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee by id
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmp) {
		Employee employee = emplrepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee Not Exist with id: "+ id));
		employee.setEmailId(updateEmp.getEmailId());
		employee.setFirstName(updateEmp.getFirstName());
		employee.setLastName(updateEmp.getLastName());
		
		Employee updatedEMployee = emplrepo.save(employee);
		
		return ResponseEntity.ok(updatedEMployee);
	}
	
	//delete Employee by id
	@DeleteMapping("employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmpById(@PathVariable Long id) {
		Employee employee = emplrepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee Not Exist with id: "+ id));
		emplrepo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put ("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
