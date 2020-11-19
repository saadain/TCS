package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.utils.DBUtils;

@Repository
public class DepartmentDAO {
	
//	private DepartmentDAO() {
//		// TODO Auto-generated constructor stub
//		}
//
//		private static DepartmentDAO dao;
//
//		public static DepartmentDAO getInstance() {
//			
//			if(dao==null) {
//				dao = new DepartmentDAO();
//				System.out.println("inside the if condition");
//				return dao;
//			}
//			return dao;
//			
//			
//		}
	
	@Autowired
	DBUtils dbUtils;
	
	public String addDepartment(Department department) {
			// TODO Auto-generated method stub
			Connection connection = dbUtils.getConnection();
			PreparedStatement preparedStatement = null;
			int result = 0;
			String insertdepartment = "insert into DEPARTMENT "
					+ "(departmentid,organizationid,name,employees) values(?,?,?,?)";
			try {
				 preparedStatement = connection.prepareStatement(insertdepartment);
				 preparedStatement.setLong(1, department.getId());
				 preparedStatement.setLong(2, department.getOrganizationId());
				 preparedStatement.setString(3, department.getName());
				 preparedStatement.setString(4, department.getEmployees().toString());
				 
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
	public String updateDepartment(long id) {
		Department department = new Department();
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		int result = 0;
		String insertdepartment = "Update DEPARTMENT Set "
				+ "organizationid = ?, name = ?,age = ?,position = ? WHERE departmentid = "
				+ department.getId();
		try {
			 preparedStatement = connection.prepareStatement(insertdepartment);
			 //preparedStatement.setLong(1, department.getId());
			 preparedStatement.setLong(1, department.getId());
			 preparedStatement.setLong(2, department.getOrganizationId());
			 preparedStatement.setString(3, department.getName());
			 preparedStatement.setString(4, department.getEmployees().toString());
			 
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
	public String deleteDepartment(long id) {
		Department department = new Department();
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		int result = 0;
		String insertdepartment = "DELETE FROM DEPARTMENT WHERE "
				+  "departmentid = " + department.getId();
		try {
			 preparedStatement = connection.prepareStatement(insertdepartment);
			 
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
	@SuppressWarnings("null")
	public Optional<Department> findById(long id) {
		
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		Employee employee = null;
		List<Employee> employees = null;
		Department department = null;
		String query = "select * from DEPARTMENT where departmentid=?";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setLong(1,id);
			
			resultSet =  preparedStatement.executeQuery();
			 
			if(resultSet.next()) {
				department = new Department();
				department.setId(resultSet.getLong("departmentid"));
				department.setOrganizationId(resultSet.getLong("organizationid"));
				department.setName(resultSet.getString("name"));
				
				String query2 = "select * from EMPLOYEE where departmentid=?";
				try {
					 preparedStatement = connection.prepareStatement(query);
					 preparedStatement.setLong(1,id);
					
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
				department.setEmployees(employees);
				
				 
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
		return Optional.of(department);
	}
	
	public Optional<List<Department>> getDepartments() {
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		Employee employee = null;
		List<Employee> employees = null;
		List<Department> departments = null;
		Department department = null;
		String query = "select * from DEPARTMENT";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 
			
			resultSet =  preparedStatement.executeQuery();
			 
			while(resultSet.next()) {
				department = new Department();
				department.setId(resultSet.getLong("departmentid"));
				department.setOrganizationId(resultSet.getLong("organizationid"));
				department.setName(resultSet.getString("name"));
				String query2 = "select * from EMPLOYEE where departmentid=?";
				try {
					 preparedStatement = connection.prepareStatement(query);
					 preparedStatement.setLong(1,resultSet.getLong("departmentid"));
					
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
				department.setEmployees(employees);
				
				departments.add(department);
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
		return Optional.of(departments);
	}
	public Optional<List<Department>> findByOrganizationId(long id) {
		Connection connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		Employee employee = null;
		List<Employee> employees = null;
		List<Department> departments = null;
		Department department = null;
		String query = "select * from DEPARTMENT where organizationid=?";
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setLong(1,id);
			
			resultSet =  preparedStatement.executeQuery();
			 
			while (resultSet.next()) {
				department = new Department();
				department.setId(resultSet.getLong("departmentid"));
				department.setOrganizationId(resultSet.getLong("organizationid"));
				department.setName(resultSet.getString("name"));
				
				String query2 = "select * from EMPLOYEE where departmentid=?";
				try {
					 preparedStatement = connection.prepareStatement(query);
					 preparedStatement.setLong(1,resultSet.getLong("departmentid"));
					
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
				department.setEmployees(employees);
				
				departments.add(department);
				 
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
		return Optional.of(departments);
	}

}