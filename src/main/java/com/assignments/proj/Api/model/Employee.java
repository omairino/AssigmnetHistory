package com.assignments.proj.Api.model;

import java.util.List;

public class Employee {

	
	private Integer id;
	private String name;
	private String password;
	private Integer managerID;
	private String email;
	private String phone;
	private List<Skill> skills;
	public Employee(Integer id, String name, String password, Integer managerID, String email, String phone,
			List<Skill> skills) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.managerID = managerID;
		this.email = email;
		this.phone = phone;
		this.skills = skills;
	}
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getManagerID() {
		return managerID;
	}
	public void setManagerID(Integer managerID) {
		this.managerID = managerID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Skill> getSkills() {
		return this.skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

	
}
