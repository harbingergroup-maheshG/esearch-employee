package com.myco.esearchemployee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myco.esearchemployee.model.Employee;
import com.myco.esearchemployee.service.EmployeeESService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class EmployeeRest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRest.class);
	
	@Autowired
	private EmployeeESService employeeESService;
	
	@PostMapping("/employee")
	public ResponseEntity<Object>  storeEmployeeInfo(@RequestBody Employee employee) {
		LOGGER.info(">>>> storeEmployeeInfo() employee : {} ", employee);
		String response = employeeESService.saveEmployeeData(employee);
		LOGGER.info("<<<< storeEmployeeInfo() response : {} ", response);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}

}
