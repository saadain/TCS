package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employee.dao.EmployeeDAO;
import com.tcs.employee.model.Employee;

@Service
public class EmployeeService {
	
//	private static EmployeeService dao;
//
//	private EmployeeService() {
//		// TODO Auto-generated constructor stub
//	}
//	public static EmployeeService getInstance() {
//		
//		if(dao==null) {
//			dao = new EmployeeService();
//			System.out.println("inside the if condition");
//			return dao;
//		}
//		return dao;
//	}
//	
//	EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	public String addEmployee(Employee employee) {
		return employeeDAO.addEmployee(employee);
	}
	public String updateEmployee(long id) {
		return employeeDAO.updateEmployee(id);
	}
	public String deleteEmployee(long id) {
		return employeeDAO.deleteEmployee(id);
	}
	public Optional<Employee> findById(long id) {
		return employeeDAO.findById(id);
	}
	public Optional<List<Employee>> getEmployees() {
		return employeeDAO.getEmployees();
	}
	public Optional<List<Employee>> findByOrganizationId(long id) {
		return employeeDAO.findByOrganizationId(id);
	}

}
