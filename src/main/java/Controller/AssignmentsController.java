package Controller;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.Assignment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Model.AssignmentHistory;

@Controller
@RequestMapping(path = "/assignments")
public class AssignmentsController {


	@GetMapping("/")
	public List<AssignmentHistory> getAllAssignments(){
		return null;
	}


	@GetMapping("/{id}/")
	public List<AssignmentHistory> getAssignmentsHistoryForEmployee(@PathVariable int id){
		return null;
	}

	@PostMapping("/")
	public AssignmentHistory insertAssignment(Assignment asn){
		return null;
	}

}
