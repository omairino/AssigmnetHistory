package Model;

import java.util.Date;
import java.util.Objects;

public class Assignment {

	private int id;
	private int employeeID;
	private int projectID;
	private String name;
	private Date startDate;
	private Date endDate;
	private String status;
	private String requestedBy;  // manager name


	public Assignment(int id, int employeeID, int projectID, String name, Date startDate, Date endDate, String status, String requestedBy) {
		this.id = id;
		this.employeeID = employeeID;
		this.projectID = projectID;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.requestedBy = requestedBy;
	}

	public static void copy(Assignment temp, Assignment item) {

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

