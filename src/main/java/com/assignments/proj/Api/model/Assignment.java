package com.assignments.proj.Api.model;

import java.util.Date;
import java.util.Objects;

public class Assignment {
    private int id;
    private String projectName;
    private String assignmentName;
    private Date startDate;
    private Date endDate;
    private String status;
    private String requestedBy;

    public Assignment(int id, String projectName, String assignmentName, Date startDate, Date endDate, String status, String requestedBy) {
        this.id = id;
        this.projectName = projectName;
        this.assignmentName = assignmentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.requestedBy = requestedBy;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public static void copy(Assignment temp, Assignment item) {

        temp.setEndDate(item.startDate);
        temp.setEndDate(item.endDate);
        temp.setStatus(item.status);
        temp.setRequestedBy(item.requestedBy);

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
