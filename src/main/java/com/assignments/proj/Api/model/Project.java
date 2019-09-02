package com.assignments.proj.Api.model;

import java.util.Objects;

public class Project {

    private int projectId;
    private String projectName;
    private String description;

    public String getDescription() {
        return description;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProjectid(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Project(int projectId, String projectName , String description) {
        this.projectId = projectId ;
        this.projectName = projectName;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId == project.projectId &&
                Objects.equals(projectName, project.projectName) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, description);
    }
}