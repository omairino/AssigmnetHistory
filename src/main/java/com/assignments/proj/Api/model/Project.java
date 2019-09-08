package com.assignments.proj.Api.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Project {

    private int id;
    private long ManagerId;
    private String projectName;
    private String description;
    private Date startDate;
    List<Skill> skills;

    public Project(int id, long managerId, String projectName, String description, Date startDate, List<Skill> skills) {
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

    public long getManagerId() {
        return ManagerId;
    }

    public void setManagerId(long managerId) {
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}