package Model;

import java.util.Date;
import java.util.Objects;

public class AssignmentHistory {

	private int id;
	private int employeeID;
	private int projectID;
	private String name;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private String status;
	private String requestedBy;  // manager name


	public AssignmentHistory(int id, int employeeID, int projectID, String name, java.util.Date startDate, java.util.Date endDate, String status, String requestedBy) {
		this.id = id;
		this.employeeID = employeeID;
		this.projectID = projectID;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.requestedBy = requestedBy;
	}

	public static void copy(AssignmentHistory temp, AssignmentHistory item) {

		temp.setEndDate(item.startDate);
		temp.setEndDate(item.endDate);
		temp.setStatus(item.status);
		temp.setRequestedBy(item.requestedBy);

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

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
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
		AssignmentHistory that = (AssignmentHistory) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	

}
