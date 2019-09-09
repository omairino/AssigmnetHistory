package com.assignments.proj.Api.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddProject {
    private String projectName;
    private int managerID;
    private String description;
    private Date startDate;
    private List<Integer> skills = new ArrayList<Integer>();

    public AddProject(String projectName, int managerID, String description, Date startDate, List<Integer> skills) {
        this.projectName = projectName;
        this.managerID = managerID;
        this.description = description;
        this.startDate = startDate;
        this.skills = skills;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
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

    public List<Integer> getSkills() {
        return skills;
    }

    public void setSkills(List<Integer> skills) {
        this.skills = skills;
    }
}
