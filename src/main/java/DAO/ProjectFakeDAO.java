package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.AssignmentHistory;
import Model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectFakeDAO  implements IDAO<Project>{
	private List<Project> project;

	@Override
	public List<Project> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project insert(Project item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project update(Project item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project delete(Project item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfPages(int id, int limit) {
		// TODO Auto-generated method stub
		return 0;
	}


}
