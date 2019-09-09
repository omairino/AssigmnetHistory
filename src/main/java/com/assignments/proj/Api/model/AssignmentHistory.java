package com.assignments.proj.Api.model;

import java.sql.Date;

public class AssignmentHistory {
    private int id;
    private int projectID;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private int requestFromManagerID;
    private String status;

    public AssignmentHistory(int id, int projectID, String projectName, Date startDate, Date endDate, int requestFromManagerID, String status) {
        this.id = id;
        this.projectID = projectID;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestFromManagerID = requestFromManagerID;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public int getRequestFromManagerID() {
        return requestFromManagerID;
    }

    public void setRequestFromManagerID(int requestFromManagerID) {
        this.requestFromManagerID = requestFromManagerID;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
