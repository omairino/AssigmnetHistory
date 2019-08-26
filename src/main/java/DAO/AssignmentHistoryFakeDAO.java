package DAO;

import java.sql.SQLException;
import java.util.List;


import Model.AssignmentHistory;
import org.springframework.stereotype.Service;
@Service
public class AssignmentHistoryFakeDAO implements IDAO<AssignmentHistory> {
	private List<AssignmentHistory> assignmentHistory;
	
	public AssignmentHistoryFakeDAO(List<AssignmentHistory> assignmentHistory) {
		super();
		this.assignmentHistory = assignmentHistory;
	}

	@Override
	public List<AssignmentHistory> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssignmentHistory find(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssignmentHistory add(AssignmentHistory item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssignmentHistory update(AssignmentHistory item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssignmentHistory delete(int item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
