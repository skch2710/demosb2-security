package com.sb2.demosb2security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb2.demosb2security.model.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {

	Employee findByEmailId(String emailId);
	
	Employee findByEmailIdIgnoreCase(String emailId);

}
