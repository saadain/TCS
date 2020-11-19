package com.tcs.employee;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tcs.employee.config.DBConfig;
import com.tcs.employee.model.Employee;
import com.tcs.employee.service.EmployeeService;

public class MainSpring {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
		EmployeeService employeeService = context.getBean(EmployeeService.class);
		Employee employee = new Employee(2L,2L,2L,"MAT",35,"senior manager");
		String result = employeeService.addEmployee(employee);
//		System.out.println(result);
//		System.out.println(employeeService.equals(employeeService2));
//		System.out.println(employeeService == employeeService2);
		context.close();
	}

}