package com.tcs.employee.model;

public class Employee {
	private Long id;
	private Long organizationid;
	private Long departmentid;
	private String name;
	private int age;
	private String position;
	
	public Employee () {
		// TODO Auto-generated constructor stub
	}

	public Employee (Long id, Long organizationid, Long departmentid, String name, int age, String position) {
		super();
		this.id = id;
		this.organizationid = organizationid;
		this.departmentid = departmentid;
		this.name = name;
		this.age = age;
		this.position = position;
	}

	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Long organizationid) {
		this.organizationid = organizationid;
	}

	public Long getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
