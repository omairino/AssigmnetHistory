package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Assignment;
import Model.AssignmentHistory;
import org.springframework.stereotype.Service;

@Service
public class AssignmentFakeDAO implements IDAO<Assignment>{
	private List<Assignment> assignment;
	public AssignmentFakeDAO(List<Assignment> assignment) {
		super();
		this.assignment = assignment;
	}

	@Override
	public List<Assignment> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment find(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment add(Assignment item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment update(Assignment item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Assignment delete(int item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
