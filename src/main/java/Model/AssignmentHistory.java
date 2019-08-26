package Model;

import java.sql.Date;

public class AssignmentHistory {
	
	private int id;
	private String name;
	private int employeeId;
	private int projectId;
	private Date startDate;
	private Date endDate;
	private boolean statusRequest;
	public AssignmentHistory(int id, String name, int employeeId, int projectId, Date startDate, Date endDate,
			boolean statusRequest) {
		super();
		this.id = id;
		this.name = name;
		this.employeeId = employeeId;
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusRequest = statusRequest;
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
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	public boolean isStatusRequest() {
		return statusRequest;
	}
	public void setStatusRequest(boolean statusRequest) {
		this.statusRequest = statusRequest;
	}
	

}
