package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Assignment;
import Model.AssignmentHistory;
import org.springframework.stereotype.Service;

@Service
public class AssignmentFakeDAO implements AssignemtsCollection<Assignment>{

	@Override
	public List<Assignment> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment insert(Assignment item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment update(Assignment item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment delete(Assignment item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfPages(int id, int limit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Assignment> getAssignmentsByUserID(int id, int currPage, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
