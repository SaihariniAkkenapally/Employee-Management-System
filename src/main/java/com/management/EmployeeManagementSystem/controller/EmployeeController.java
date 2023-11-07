package com.management.EmployeeManagementSystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.management.EmployeeManagementSystem.model.Department;
import com.management.EmployeeManagementSystem.model.Employee;
import com.management.EmployeeManagementSystem.service.DepartmentService;
import com.management.EmployeeManagementSystem.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createEmployee");
		return modelAndView;
	}

	@PostMapping("/create")
	public ModelAndView createEmployee(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("departmentId") String departmentId) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setEmail(email);
		employee.setCreatedAt(System.currentTimeMillis());
		employee.setUpdatedAt(System.currentTimeMillis());
		ModelAndView modelAndView = new ModelAndView();
		Optional<Department> department = departmentService.getDepartmentById(departmentId);
		if (department.isPresent()) {
			Department existingDepartment = new Department();
			existingDepartment.setDepartmentId(department.get().getDepartmentId());
			existingDepartment.setDepartmentName(department.get().getDepartmentName());
			employee.setDepartment(existingDepartment);
			employeeService.createEmployee(employee);
			modelAndView.setViewName("success");
		} else
			modelAndView.setViewName("fail");
		return modelAndView;
	}

	@RequestMapping("/read")
	public ModelAndView read() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("readEmployee");
		return modelAndView;
	}

	@PostMapping("/read")
	public ModelAndView readEmployee(@RequestParam("Id") Long id) {
		Optional<Employee> employee = employeeService.readEmployee(id);
		ModelAndView modelAndView = new ModelAndView();
		if (employee.isEmpty())
			modelAndView.setViewName("fail");
		else {
			modelAndView.addObject("employee", employee.get());
			modelAndView.setViewName("getEmployeeResults");
		}
		return modelAndView;

	}

	@RequestMapping("/update")
	public ModelAndView update() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("updateEmployee");
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView updateEmployee(@RequestParam("Id") Long id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("departmentId") String departmentId) {
		ModelAndView modelAndView = new ModelAndView();
		Optional<Employee> employee1 = employeeService.readEmployee(id);
		Optional<Department> department = departmentService.getDepartmentById(departmentId);
		if (employee1.isPresent()) {
			Employee employee = new Employee();
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmail(email);
			employee.setEmployeeId(id);
			employee.setUpdatedAt(System.currentTimeMillis());
			employee.setCreatedAt(employee1.get().getCreatedAt());
			Department existingDepartment = new Department();
			existingDepartment.setDepartmentId(department.get().getDepartmentId());
			existingDepartment.setDepartmentName(department.get().getDepartmentName());
			employee.setDepartment(existingDepartment);
			employeeService.updateEmployee(employee);
			modelAndView.setViewName("success");
		} else
			modelAndView.setViewName("fail");
		return modelAndView;
	}

	@RequestMapping("/delete")
	public ModelAndView delete() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("deleteEmployee");
		return modelAndView;
	}

	@PostMapping("/delete")
	public ModelAndView deleteEmployee(@RequestParam("Id") Long id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("departmentId") String departmentId) {
		return employeeService.deleteEmployee(id);

	}
}