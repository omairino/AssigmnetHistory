package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.AssignmentHistory;
import Model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectFakeDAO  implements IDAO<Project>{
	private List<Project> project;

<<<<<<< HEAD
	@Override
	public List<Project> getAllItems() {
		// TODO Auto-generated method stub
=======
	public ProjectFakeDAO(List<Project> project) {
		super();
		this.project = project;
	}


	@Override
	public List<Project> getAllItems() {
>>>>>>> amjad
		return null;
	}

	@Override
	public Project insert(Project item) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> amjad
		return null;
	}

	@Override
	public Project update(Project item) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> amjad
		return null;
	}

	@Override
	public Project delete(Project item) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> amjad
		return null;
	}

	@Override
	public int numberOfPages(int id, int limit) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return 0;
	}


=======
		return 0;
	}
>>>>>>> amjad
}
