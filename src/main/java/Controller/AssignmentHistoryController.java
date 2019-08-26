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

import DAO.AssignemtsCollection;
import DAO.AssignmentHistoryFakeDAO;
import Model.Assignment;


@RestController
@RequestMapping("/assignments")
public class AssignmentHistoryController {
	

	@GetMapping("")
	public @ResponseBody List<Assignment> byid(@PathVariable int id) {
		AssignmentHistoryFakeDAO assignment = new AssignmentHistoryFakeDAO();
		return assignment.getAllItems();
	}
	


}
