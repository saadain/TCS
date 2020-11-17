package com.tcs.employee;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.model.Organization;
import com.tcs.employee.service.DepartmentService;
import com.tcs.employee.service.EmployeeService;
import com.tcs.employee.service.OrganizationService;

public class Main {
	
	
	long d = 1;
	public static void main(String[] args) {
		Employee emp = new Employee(1L,1L,1L,"sam",25,"manager");
		Department dep = new Department(1L,1L,"HR",null);
		Organization org = new Organization(1L,"TCS","USA",null,null);
		
		EmployeeService employee = EmployeeService.getInstance();
		DepartmentService department = DepartmentService.getInstance();
		OrganizationService organization = OrganizationService.getInstance();
		
		int turn = 0;
		while(turn < 6) {
			
			switch(turn) {
				case 0:
					employee.addEmployee(emp);
					department.addDepartment(dep);
					organization.addorganization(org);
				case 1:
					employee.updateEmployee(1);
					department.updateDepartment(1);
					organization.updateorganization(1);
				case 2:
					employee.deleteEmployee(2);
					department.deleteDepartment(2);
					organization.deleteorganization(2);
				case 3:
					employee.findById(1);
					department.findById(1);
					organization.findById(1);
				case 4:
					employee.getEmployees();
					department.getDepartments();
					organization.getOrganizations();
				case 5:
					employee.findByOrganizationId(1);
					department.findByOrganizationId(1);
			}
			turn++;
		}

	}

}
