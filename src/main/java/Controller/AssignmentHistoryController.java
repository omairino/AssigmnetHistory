package Controller;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import DAO.AssignmentHistoryFakeDAO;
import Model.AssignmentHistory;

@RestController
@RequestMapping("/AssimentHistory")
public class AssignmentHistoryController {
	
	@GetMapping("")
	public @ResponseBody List<AssignmentHistory> all(){
		try {
			return  new ArrayList<AssignmentHistory>(AssignmentHistoryFakeDAO.getinstance().findAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
	}
	@GetMapping("/{id}")
	public @ResponseBody AssignmentHistory byid(@PathVariable int id) {
//		return MovieDAO.getinstance().find(id);
	}
	
	@PostMapping("")
	public String newAssignmentHistory(@RequestBody AssignmentHistory movie) {
//		boolean isOK = movies.add(movie);
//		return new Boolean(isOK).toString();
	}

}
