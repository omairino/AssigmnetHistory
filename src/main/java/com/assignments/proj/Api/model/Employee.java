package com.assignments.proj.Api.model;

import java.util.List;

public class Employee {


	private int id;
	private int managerID;
	private String name;
	private List <TechnicalSkill> technicalSkills;
	private List <ProductSkill> productSkills;
	private String img;

	public Employee(int id, int managerID, String name, List<TechnicalSkill> technicalSkills, List<ProductSkill> productSkills, String img) {
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

	public List<TechnicalSkill> getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(List<TechnicalSkill> technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	public List<ProductSkill> getProductSkills() {
		return productSkills;
	}

	public void setProductSkills(List<ProductSkill> productSkills) {
		this.productSkills = productSkills;
	}

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
