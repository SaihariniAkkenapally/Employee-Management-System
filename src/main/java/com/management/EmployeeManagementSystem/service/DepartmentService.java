package com.management.EmployeeManagementSystem.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.management.EmployeeManagementSystem.model.Department;
import com.management.EmployeeManagementSystem.repository.DepartmentRepository;

@Service
public class DepartmentService {
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	@Qualifier("DepartmentRepository")
	DepartmentRepository departmentRepository;

	public Department createDepartment(Department department) {
		Department response = null;
		try {
			response = departmentRepository.save(department);

		} catch (Exception e) {
			logger.error("Error while creating department {}", e.toString());
		}
		return response;
	}

	public Optional<Department> getDepartmentById(String departmentId) {
		Optional<Department> response = null;
		try {
			response = departmentRepository.findById(departmentId);

		} catch (Exception e) {
			logger.error("Error while getting department {}", e.toString());
		}
		return response;
	}

	public Department updateDepartment(Department department) {

		Department response = null;
		try {
			response = departmentRepository.save(department);

		} catch (Exception e) {
			logger.error("Error while updating department {}", e.toString());
		}
		return response;
	}

	public ModelAndView deleteDepartment(String departmentId, ModelAndView modelAndView) {
		try {
			departmentRepository.deleteById(departmentId);
			modelAndView.setViewName("success");
		} catch (Exception e) {
			logger.error("Error while deleting department {}", e.toString());
			modelAndView.setViewName("fail");
		}
		return modelAndView;
	}
}