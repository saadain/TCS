package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.dao.EmployeeDAO;
import com.tcs.employee.dao.OrganizationDAO;
import com.tcs.employee.model.Organization;

public class OrganizationService {
	
	private static OrganizationService dao;

	private OrganizationService() {
		// TODO Auto-generated constructor stub
	}
	public static OrganizationService getInstance() {
		
		if(dao==null) {
			dao = new OrganizationService();
			System.out.println("inside the if condition");
			return dao;
		}
		return dao;
	}
	
	OrganizationDAO organizationdao = OrganizationDAO.getInstance();
	
	public String addorganization(Organization organization) {
		return organizationdao.addOrganization(organization);
	}
	
	public String updateorganization(long id) {
		return organizationdao.updateOrganization(id);
	}
	
	public String deleteorganization(long id) {
		return organizationdao.deleteOrganization(id);
	}
	public Optional<Organization> findById(long id) {
		return organizationdao.findById(id);
	}
	
	public Optional<List<Organization>> getOrganizations() {
		return organizationdao.getOrganizations();
	}

}
