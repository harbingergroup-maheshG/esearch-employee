package com.myco.esearchemployee.service;

import java.io.IOException;
import java.util.List;

import com.myco.esearchemployee.model.Employee;

public interface EmployeeESService {

	List<Employee> checkValidEmployee(String userId, String password) throws IOException;

	String saveEmployeeData(Employee employee);

	List<Employee> getEmployeeList();

}
