package com.springboot.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.crud.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	

}
