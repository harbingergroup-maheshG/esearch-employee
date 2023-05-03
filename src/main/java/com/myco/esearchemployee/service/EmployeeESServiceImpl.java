package com.myco.esearchemployee.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myco.esearchemployee.model.Employee;
import com.myco.esearchemployee.repository.ElasticSearchQuery;

@Service
public class EmployeeESServiceImpl implements EmployeeESService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeESServiceImpl.class);

	@Autowired
	private ElasticSearchQuery elasticSearchQuery;

	@Override
	public List<Employee> checkValidEmployee(String userId, String password) {
		LOGGER.info(">>>> checkValidEmployee() : userId : {} ", userId);
		List<Employee> employees = new ArrayList<>();
		try {
			 Employee employee = elasticSearchQuery.getDocumentById(userId);
			if (null!=employee) {
				if (userId.equals("HSADMIN01") && password.equals(employee.getPassword())) {
					return getEmployeeList();
				}else if(password.equals(employee.getPassword())){
					LOGGER.info("<<<< checkValidEmployee() : employee : {} ", employee);
					employees.add(employee);
					return employees;
				}
			}
			
		}catch(Exception e ) {
			LOGGER.error(">>>> failed to fetch employee for userId : {} , exception : {} ", userId, e.getMessage());
		}
		return null;
	}

	@Override
	public String saveEmployeeData(Employee employee) {
		LOGGER.info(">>>> saveEmployeeData() : employee : {} ", employee);
		String response = "";
		try {
			response = elasticSearchQuery.createOrUpdateDocument(employee);
		} catch (Exception e) {
			LOGGER.error(">>>> error on saveEmployeeData() : {} , exception : {} ", employee, e.getMessage());
		}
		return response;
	}

	@Override
	public List<Employee> getEmployeeList() {
		LOGGER.info(">>>> getEmployeeList() ");
		try {
			List<Employee> employees =  elasticSearchQuery.searchAllDocuments();
			if(null!=employees && !employees.isEmpty()) {
				LOGGER.info(">>>> getEmployeeList(), employees:{}" , employees.toString());
				return employees;
			}
		} catch (Exception e) {
			LOGGER.error(">>>> error on getEmployeeList(), exception : {} ", e.getMessage());
		}
		return null;
	}  
	  
}
