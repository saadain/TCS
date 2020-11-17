package com.tcs.employee.dao;

import com.tcs.employee.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


import com.tcs.employee.model.Employee;

public class EmployeeDAO {
	
	private EmployeeDAO() {
	// TODO Auto-generated constructor stub
	}

	private static EmployeeDAO dao;

	public static EmployeeDAO getInstance() {
		
		if(dao==null) {
			dao = new EmployeeDAO();
			System.out.println("inside the if condition");
			return dao;
		}
		return dao;
		
		
	}
	
	public String addEmployee(Employee employee) {
			// TODO Auto-generated method stub
			Connection connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = null;
			int result = 0;
			String insertemployee = "insert into EMPLOYEE "
					+ "(employeeid,organizationid,departmentid,name,age,position) values(?,?,?,?,?,?)";
			try {
				 preparedStatement = connection.prepareStatement(insertemployee);
				 preparedStatement.setLong(1, employee.getId());
				 preparedStatement.setLong(2, employee.getOrganizationid());
				 preparedStatement.setLong(3, employee.getDepartmentid());
				 preparedStatement.setString(4, employee.getName());
				 preparedStatement.setLong(5, employee.getAge());
				 preparedStatement.setString(6, employee.getPosition());
				 
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
	public String updateEmployee(long id) {
		Employee employee = new Employee();
		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		int result = 0;
		String insertemployee = "Update EMPLOYEE Set "
				+ "organizationid = ?, departmentid = ?,name = ?,age = ?,position = ? WHERE employeeid = "
				+ employee.getId();
		try {
			 preparedStatement = connection.prepareStatement(insertemployee);
			 //preparedStatement.setLong(1, employee.getId());
			 preparedStatement.setLong(1, employee.getOrganizationid());
			 preparedStatement.setLong(2, employee.getDepartmentid());
			 preparedStatement.setString(3, employee.getName());
			 preparedStatement.setLong(4, employee.getAge());
			 preparedStatement.setString(5, employee.getPosition());
			 
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
	public String deleteEmployee(long id) {
		Employee employee = new Employee();
		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		int result = 0;
		String insertemployee = "DELETE FROM EMPLOYEE WHERE "
				+  "employeeid = " + employee.getId();
		try {
			 preparedStatement = connection.prepareStatement(insertemployee);
			 
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
	public Optional<Employee> findById(long id) {
		
		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		Employee employee = null;
		String query = "select * from EMPLOYEE where employeeid=?";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setLong(1,id);
			
			resultSet =  preparedStatement.executeQuery();
			 
			if(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getLong("employeeid"));
				employee.setOrganizationid(resultSet.getLong("organizationid"));
				employee.setDepartmentid(resultSet.getLong("departmentid"));
				employee.setName(resultSet.getString("name"));
				employee.setAge(resultSet.getInt("age"));
				employee.setPosition(resultSet.getString("position"));
				
				 
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
			DBUtils.closeConnection(connection);
		}
		return Optional.of(employee);
	}
	
	public Optional<List<Employee>> getEmployees() {
		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> emps = null;
		Employee employee = null;
		String query = "select * from EMPLOYEE";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 
			
			resultSet =  preparedStatement.executeQuery();
			 
			while(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getLong("employeeid"));
				employee.setOrganizationid(resultSet.getLong("organizationid"));
				employee.setDepartmentid(resultSet.getLong("departmentid"));
				employee.setName(resultSet.getString("name"));
				employee.setAge(resultSet.getInt("age"));
				employee.setPosition(resultSet.getString("position"));
				
				emps.add(employee);
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
			DBUtils.closeConnection(connection);
		}
		return Optional.of(emps);
	}
	public Optional<List<Employee>> findByOrganizationId(long id) {
		Connection connection = DBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> emps = null;
		Employee employee = null;
		String query = "select * from EMPLOYEE where organizationid=?";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setLong(1,id);
			
			resultSet =  preparedStatement.executeQuery();
			 
			if(resultSet.next()) {
				employee = new Employee();
				employee.setId(resultSet.getLong("employeeid"));
				employee.setOrganizationid(resultSet.getLong("organizationid"));
				employee.setDepartmentid(resultSet.getLong("departmentid"));
				employee.setName(resultSet.getString("name"));
				employee.setAge(resultSet.getInt("age"));
				employee.setPosition(resultSet.getString("position"));
				
				emps.add(employee);
				 
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
			DBUtils.closeConnection(connection);
		}
		return Optional.of(emps);
	}

}