package com.assignments.proj.Api.model;


import java.sql.Date;
import java.util.List;

public class Project {

    private int id;
    private String name;
    private String description;
    private Date startDate;
    private List<TechnicalSkill> technicalSkill;
    private List<ProductSkill> productSkill;

    public Project(int id, String name, String description, Date startDate, List<TechnicalSkill> technicalSkill, List<ProductSkill> productSkill) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.technicalSkill = technicalSkill;
        this.productSkill = productSkill;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<TechnicalSkill> getTechnicalSkill() {
        return technicalSkill;
    }

    public void setTechnicalSkill(List<TechnicalSkill> technicalSkill) {
        this.technicalSkill = technicalSkill;
    }

    public List<ProductSkill> getProductSkill() {
        return productSkill;
    }

    public void setProductSkill(List<ProductSkill> productSkill) {
        this.productSkill = productSkill;
    }
}