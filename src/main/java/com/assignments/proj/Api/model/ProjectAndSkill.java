package com.assignments.proj.Api.model;

import java.sql.Date;
import java.util.List;

public class ProjectAndSkill {
    private int id;
    private int ManagerId;
    private String projectName;
    private String description;
    private Date startDate;
    private int skills;

    public ProjectAndSkill(int id, int managerId, String projectName, String description, Date startDate, int skills) {
        this.id = id;
        ManagerId = managerId;
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerId() {
        return ManagerId;
    }

    public void setManagerId(int managerId) {
        ManagerId = managerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public int getSkills() {
        return skills;
    }

    public void setSkills(int skills) {
        this.skills = skills;
    }
}
