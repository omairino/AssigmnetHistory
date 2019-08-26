package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.AssignmentHistory;
import Model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectFakeDAO  implements IDAO<Project>{
	private List<Project> project;

	public ProjectFakeDAO(List<Project> project) {
		super();
		this.project = project;
	}

	@Override
	public List<Project> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project find(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project add(Project item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project update(Project item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project delete(int item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
