package com.assignments.proj.Api.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.assignments.proj.Api.DAO.AssignmentHistoryFakeDAO;
import com.assignments.proj.Api.Model.AssignmentHistory;

class ObjectKey{
    long id;
    int page;
	int limit;
	
	  public ObjectKey(long id, int page, int limit) {
			super();
			this.id = id;
			this.page = page;
			this.limit = limit;
		}

}

@RestController
@RequestMapping(path = "/assignments")
public class AssignmentHistoryController {
	

	@Autowired
	private AssignmentHistoryFakeDAO assignmentHistory ;


	@GetMapping("")
	public @ResponseBody List<AssignmentHistory> getAssignmentsHistoryForEmployee(@RequestParam int empid, @RequestParam int pageNumber,@RequestParam int numberOfAssignments  ){
			if(numberOfAssignments != 0)
				return assignmentHistory.getAssignmentsByUserID(empid,pageNumber,numberOfAssignments);
			else
				return assignmentHistory.getAssignmentsByUserID(empid,pageNumber,10);
	}
	


}
