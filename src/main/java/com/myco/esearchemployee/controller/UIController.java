package com.myco.esearchemployee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myco.esearchemployee.model.Employee;
import com.myco.esearchemployee.service.EmployeeESService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UIController {

	private static final String EMPLOYEE = "employee";

	private static final String VERIFY_LOGIN_VERIFIED_EMPLOYEE = "<<<< verifyLogin() verified employee : {} ";

	private static final Logger LOGGER = LoggerFactory.getLogger(UIController.class);

	@Autowired
	private EmployeeESService employeeESService;

	HttpSession session;

	@GetMapping("/")
	public String viewHomePage(Model model) throws IOException {
		return "login";
	}

	@PostMapping("/login")
	public String verifyLogin(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "password") String password, Model model) throws IOException {
		LOGGER.info(">>>> verifyLogin() userId : {} ", userId);
		List<Employee> employees = employeeESService.checkValidEmployee(userId, password);
		if (null != employees && !employees.isEmpty()) {
			if (1 == employees.size()) {
				Employee employee = employees.get(0);
				model.addAttribute(EMPLOYEE, employee);
				LOGGER.info(VERIFY_LOGIN_VERIFIED_EMPLOYEE, employee);
				return EMPLOYEE;
			}
			model.addAttribute("employees", employees);
		} else {
			LOGGER.info("<<<< verifyLogin() invalid user : {} or password : {} ", userId, password);
			model.addAttribute("message", "Invalid login details, Please enter valid Details!!");
			return "login";
		}
		LOGGER.info(VERIFY_LOGIN_VERIFIED_EMPLOYEE, employees);
		return "employees";
	}

}
