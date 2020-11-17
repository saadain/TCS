package com.tcs.employee.service;


import java.util.List;
import java.util.Optional;

import com.tcs.employee.dao.DepartmentDAO;
import com.tcs.employee.dao.EmployeeDAO;
import com.tcs.employee.model.Department;

public class DepartmentService {
	

	private static DepartmentService dao;

	private DepartmentService() {
		// TODO Auto-generated constructor stub
	}
	public static DepartmentService getInstance() {
		
		if(dao==null) {
			dao = new DepartmentService();
			System.out.println("inside the if condition");
			return dao;
		}
		return dao;
	}
	
	DepartmentDAO departmentdao = DepartmentDAO.getInstance();
	
	public String addDepartment(Department department) {
		return departmentdao.addDepartment(department);
	}
	
	public String updateDepartment(long id) {
		return departmentdao.updateDepartment(id);
	}
	
	public String deleteDepartment(long id) {
		return departmentdao.deleteDepartment(id);
	}
	public Optional<Department> findById(long id) {
		return departmentdao.findById(id);
	}
	
	public Optional<List<Department>> getDepartments() {
		return departmentdao.getDepartments();
	}
	public Optional<List<Department>> findByOrganizationId(long id) {
		return departmentdao.findByOrganizationId(id);
	}

}
