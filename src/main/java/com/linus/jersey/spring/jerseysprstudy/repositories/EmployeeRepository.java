package com.linus.jersey.spring.jerseysprstudy.repositories;

import com.linus.jersey.spring.jerseysprstudy.bo.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRepository {

	public Employee getEmployee(Long id) {
		return new Employee();
	}
}
