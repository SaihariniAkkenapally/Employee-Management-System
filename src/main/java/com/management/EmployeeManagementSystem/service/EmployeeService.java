package com.management.EmployeeManagementSystem.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.management.EmployeeManagementSystem.model.Employee;
import com.management.EmployeeManagementSystem.repository.EmployeeRepository;

@Service
public class EmployeeService {
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	@Qualifier("EmployeeRepository")
	EmployeeRepository employeeRepository;

	public Employee createEmployee(Employee employee) {
		Employee response = null;
		try {
			response = employeeRepository.save(employee);
		} catch (Exception e) {
			logger.error("Error while creating employee {}", e.toString());
		}
		return response;
	}

	public Optional<Employee> readEmployee(Long id) {
		Optional<Employee> response = null;
		try {
			response = employeeRepository.findById(id);
		} catch (Exception e) {
			logger.error("Error while getting employee {}", e.toString());
		}
		return response;
	}

	public Employee updateEmployee(Employee employee) {
		Employee response = null;
		try {
			response = employeeRepository.save(employee);
		} catch (Exception e) {
			logger.error("Error while updating employee {}", e.toString());
		}
		return response;
	}

	public ModelAndView deleteEmployee(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			employeeRepository.deleteById(id);
			modelAndView.setViewName("success");
		} catch (Exception e) {
			logger.error("Error while deleting employee {}", e.toString());
			modelAndView.setViewName("fail");
		}
		return modelAndView;
	}

}