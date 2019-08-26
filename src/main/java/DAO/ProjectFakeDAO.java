package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.AssignmentHistory;
import Model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectFakeDAO implements IDAO<Project>{
	private List<Project> project;

	public ProjectFakeDAO(List<Project> project) {
		super();
		this.project = project;
	}

	@Override
	public List<Project> getAllItems() {
		return project;
	}

	@Override
	public Project insert(Project item) {
		return null;
	}

	@Override
	public Project update(Project item) {
		return null;
	}

	@Override
	public Project delete(Project item) {
		return null;
	}

	@Override
	public int numberOfPages(int id, int limit) {
		return 0;
	}






}
