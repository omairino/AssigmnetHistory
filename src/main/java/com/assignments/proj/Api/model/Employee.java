package com.assignments.proj.Api.model;

import java.util.List;

public class Employee {


	private int id;
	private int managerID;
	private String name;
	private List <Skills> technicalSkills;
	private List <Skills> productSkills;
	private String img;

	public Employee(int id, int managerID, String name, List<Skills> technicalSkills, List<Skills> productSkills, String img) {
		this.id = id;
		this.managerID = managerID;
		this.name = name;
		this.technicalSkills = technicalSkills;
		this.productSkills = productSkills;
		this.img = img;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Skills> getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(List<Skills> technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	public List<Skills> getProductSkills() {
		return productSkills;
	}

	public void setProductSkills(List<Skills> productSkills) {
		this.productSkills = productSkills;
	}

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
