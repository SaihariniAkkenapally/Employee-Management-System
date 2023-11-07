package com.management.EmployeeManagementSystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.management.EmployeeManagementSystem.model.Department;
import com.management.EmployeeManagementSystem.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createDepartment");
		return modelAndView;
	}

	@PostMapping("/create")
	public ModelAndView createDepartment(@RequestParam("departmentId") String departmentId,
			@RequestParam("departmentName") String departmentName) {
		Department department = new Department();
		department.setDepartmentId(departmentId);
		department.setDepartmentName(departmentName);
		department.setCreatedAt(System.currentTimeMillis());
		department.setUpdatedAt(System.currentTimeMillis());
		ModelAndView modelAndView = new ModelAndView();
		Department response = departmentService.createDepartment(department);
		if (response != null) {
			modelAndView.setViewName("success");
		} else
			modelAndView.setViewName("fail");
		return modelAndView;
	}

	@RequestMapping("/read")
	public ModelAndView read() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("readDepartment");
		return modelAndView;
	}

	@PostMapping("/read")
	public ModelAndView readDepartment(@RequestParam("departmentId") String departmentId) {
		Optional<Department> department = departmentService.getDepartmentById(departmentId);
		ModelAndView modelAndView = new ModelAndView();
		if (!department.isPresent())
			modelAndView.setViewName("fail");
		else {
			modelAndView.addObject("department", department.get());
			modelAndView.setViewName("getDepartmentResults");
		}
		return modelAndView;

	}

	@RequestMapping("/update")
	public ModelAndView update() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("updateDepartment");
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView updateDepartment(@RequestParam("departmentId") String departmentId,
			@RequestParam("departmentName") String departmentName) {
		ModelAndView modelAndView = new ModelAndView();
		Optional<Department> department = departmentService.getDepartmentById(departmentId);
		if (department.isPresent()) {
			Department existingDepartment = department.get();
			existingDepartment.setDepartmentName(departmentName);
			existingDepartment.setCreatedAt(department.get().getCreatedAt());
			existingDepartment.setUpdatedAt(System.currentTimeMillis());
			departmentService.updateDepartment(existingDepartment);
			modelAndView.setViewName("success");
		} else
			modelAndView.setViewName("fail");
		return modelAndView;
	}

	@RequestMapping("/delete")
	public ModelAndView delete() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("deleteDepartment");
		return modelAndView;
	}

	@PostMapping("/delete")
	public ModelAndView deleteDepartment(@RequestParam("departmentId") String departmentId) {
		ModelAndView modelAndView = new ModelAndView();
		return departmentService.deleteDepartment(departmentId, modelAndView);
	}
}