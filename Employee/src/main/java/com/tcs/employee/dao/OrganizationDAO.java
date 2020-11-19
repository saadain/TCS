package com.tcs.employee.dao;

import java.sql.Connection;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.model.Organization;
import com.tcs.employee.utils.DBUtils;

@Repository
public class OrganizationDAO {
	
//	private OrganizationDAO() {
//		// TODO Auto-generated constructor stub
//		}
//
//		private static OrganizationDAO dao;
//
//		public static OrganizationDAO getInstance() {
//			
//			if(dao==null) {
//				dao = new OrganizationDAO();
//				System.out.println("inside the if condition");
//				return dao;
//			}
//			return dao;
//			
//			
//		}
	
	@Autowired
	DBUtils dbUtils;
	
	public String addOrganization(Organization organization) {
			// TODO Auto-generated method stub
			Connection connection = dbUtils.getConnection();
			PreparedStatement preparedStatement = null;
			int result = 0;
			String insertorganization = "insert into ORGANIZATION "
					+ "(id,name,departments,employee) values(?,?,?,?)";
			try {
				 preparedStatement = connection.prepareStatement(insertorganization);
				 preparedStatement.setLong(1, organization.getId());
				 preparedStatement.setString(3, organization.getName());
				 preparedStatement.setString(5, organization.getDepartments().toString());
				 preparedStatement.setString(5, organization.getEmployees().toString());
				 
				 result = preparedStatement.executeUpdate();
				 
				 if(result>0)
				 {
					 connection.commit();
					 return "success";
					 
				 }
				 else {
					 return "fail";
				 }
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				}
			return null;
	}
	
	public String updateOrganization(long id) {
		Organization organization = new Organization();
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		int result = 0;
		String insertorganization = "Update ORGANIZATION Set "
				+ "organizationid = ?, name = ?,age = ?,position = ? WHERE organizationid = "
				+ organization.getId();
		try {
			 preparedStatement = connection.prepareStatement(insertorganization);
			 //preparedStatement.setLong(1, organization.getId());
			 preparedStatement.setLong(1, organization.getId());
			 preparedStatement.setString(2, organization.getName());
			 preparedStatement.setString(3, organization.getDepartments().toString());
			 preparedStatement.setString(4, organization.getEmployees().toString());
			 
			 result = preparedStatement.executeUpdate();
			 
			 if(result>0)
			 {
				 connection.commit();
				 return "success";
				 
			 }
			 else {
				 return "fail";
			 }
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			}
		return null;
	}
	
	public String deleteOrganization(long id) {
		Organization organization = new Organization();
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		int result = 0;
		String insertorganization = "DELETE FROM ORGANIZATION WHERE "
				+  "organizationid = " + organization.getId();
		try {
			 preparedStatement = connection.prepareStatement(insertorganization);
			 
			 result = preparedStatement.executeUpdate();
			 
			 if(result>0)
			 {
				 connection.commit();
				 return "success";
				 
			 }
			 else {
				 return "fail";
			 }
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			}
		return null;
	}
	
	public Optional<Organization> findById(long id) {
		
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		Department department = null;
		Employee employee = null;
		List<Department> departments = null;
		List<Employee> employees = null;
		Organization organization = null;
		String query = "select * from ORGANIZATION where organizationid=?";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setLong(1,id);
			
			resultSet =  preparedStatement.executeQuery();
			 
			if(resultSet.next()) {
				organization = new Organization();
				organization.setId(resultSet.getLong("organizationid"));
				organization.setName(resultSet.getString("name"));
				
				String query2 = "select * from EMPLOYEE where organizationid=?";
				try {
					 preparedStatement = connection.prepareStatement(query);
					 preparedStatement.setLong(1,resultSet.getLong("organizationid"));
					
					resultSet2 =  preparedStatement.executeQuery();
					while (resultSet2.next()) {
						employee = new Employee();
						employee.setId(resultSet2.getLong("employeeid"));
						employee.setOrganizationid(resultSet2.getLong("organizationid"));
						employee.setDepartmentid(resultSet2.getLong("departmentid"));
						employee.setName(resultSet2.getString("name"));
						employee.setAge(resultSet2.getInt("age"));
						employee.setPosition(resultSet2.getString("position"));
						employees.add(employee);
					}
						
				} catch (SQLException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Optional.empty();
				}
				organization.setEmployees(employees);
				
				query2 = "select * from DEPARMTENT where organizationid=?";
				try {
					 preparedStatement = connection.prepareStatement(query);
					 preparedStatement.setLong(1,resultSet.getLong("organizationid"));
					
					resultSet2 =  preparedStatement.executeQuery();
					while (resultSet2.next()) {
						department = new Department();
						department.setId(resultSet2.getLong("departmentid"));
						department.setOrganizationId(resultSet2.getLong("organizationid"));
						department.setName(resultSet2.getString("name"));
						department.setEmployees(employees);
						
						departments.add(department);
					}
						
				} catch (SQLException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Optional.empty();
				}
				
				organization.setDepartments(departments);
			}
			 
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Optional.empty();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.of(organization);
	}
	
	public Optional<List<Organization>> getOrganizations() {
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		Department department = null;
		Employee employee = null;
		List<Department> departments = null;
		List<Employee> employees = null;
		List<Organization> emps = null;
		Organization organization = null;
		String query = "select * from ORGANIZATION";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 
			
			resultSet =  preparedStatement.executeQuery();
			 
			while(resultSet.next()) {
				organization = new Organization();
				organization.setId(resultSet.getLong("organizationid"));
				organization.setName(resultSet.getString("name"));
				
				String query2 = "select * from EMPLOYEE where organizationid=?";
				try {
					 preparedStatement = connection.prepareStatement(query);
					 preparedStatement.setLong(1,resultSet.getLong("organizationid"));
					
					resultSet2 =  preparedStatement.executeQuery();
					while (resultSet2.next()) {
						employee = new Employee();
						employee.setId(resultSet2.getLong("employeeid"));
						employee.setOrganizationid(resultSet2.getLong("organizationid"));
						employee.setDepartmentid(resultSet2.getLong("departmentid"));
						employee.setName(resultSet2.getString("name"));
						employee.setAge(resultSet2.getInt("age"));
						employee.setPosition(resultSet2.getString("position"));
						employees.add(employee);
					}
						
				} catch (SQLException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Optional.empty();
				}
				organization.setEmployees(employees);
				
				query2 = "select * from DEPARMTENT where organizationid=?";
				try {
					 preparedStatement = connection.prepareStatement(query);
					 preparedStatement.setLong(1,resultSet.getLong("organizationid"));
					
					resultSet2 =  preparedStatement.executeQuery();
					while (resultSet2.next()) {
						department = new Department();
						department.setId(resultSet2.getLong("departmentid"));
						department.setOrganizationId(resultSet2.getLong("organizationid"));
						department.setName(resultSet2.getString("name"));
						department.setEmployees(employees);
						
						departments.add(department);
					}
						
				} catch (SQLException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					return Optional.empty();
				}
				
				organization.setDepartments(departments);
			}
				
			
			 
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Optional.empty();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.of(emps);
	}
	
	

}