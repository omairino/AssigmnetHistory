package com.assignments.proj.Api.model;

import java.sql.Date;

public class Assignment {
    private int id;
    private int projectID;
    private int employeeID;
    private Date startDate;
    private Date endDate;
    private int requestFromManagerID;
    private int requestToManagerID;
    private String status;

    public Assignment(int id, int projectID, int employeeID, Date startDate, Date endDate, int requestFromManagerID, int requestToManagerID, String status) {
        this.id = id;
        this.projectID = projectID;
        this.employeeID = employeeID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestFromManagerID = requestFromManagerID;
        this.requestToManagerID = requestToManagerID;
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

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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

    public int getRequestToManagerID() {
        return requestToManagerID;
    }

    public void setRequestToManagerID(int requestToManagerID) {
        this.requestToManagerID = requestToManagerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
