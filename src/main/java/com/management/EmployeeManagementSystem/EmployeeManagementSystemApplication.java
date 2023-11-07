package com.management.EmployeeManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.management.EmployeeManagementSystem.repository")
@EntityScan(basePackages = "com.management.EmployeeManagementSystem.model")
//@EnableMongoRepositories(basePackages = "com.management.EmployeeManagementSystem.repository")
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

}
