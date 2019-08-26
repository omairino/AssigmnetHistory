package com.assignments.proj.Api.Controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.assignments.proj.Api.DAO.ProjectFakeDAO;
import com.assignments.proj.Api.Model.AssignmentHistory;
import com.assignments.proj.Api.Model.Project;

import org.json.simple.JSONObject;;
@RestController
@RequestMapping(path = "/assignments")
public class AssignmentHistoryController {
	

	@Autowired
	private AssignmentHistoryFakeDAO assignmentHistory ;
	@Autowired
	private ProjectFakeDAO p ;	


	@GetMapping("")
	public @ResponseBody List<JSONObject> getAssignmentsHistoryForEmployee(@RequestParam int empid, @RequestParam int pageNumber,@RequestParam int numberOfAssignments  ){
		List<AssignmentHistory>  a;
		if(numberOfAssignments != 0) {
			 a = assignmentHistory.getAssignmentsByUserID(empid,pageNumber,numberOfAssignments);
		}
		else{
			 a = assignmentHistory.getAssignmentsByUserID(empid,pageNumber,10);
		}
		
		JSONObject result= new JSONObject();
		JSONObject message= new JSONObject();
		List<JSONObject>  json =  new ArrayList<JSONObject>();
		if(a.size() > 0) {
			message.put("responseData", "True");
			message.put("msg", "success");
			
		for(AssignmentHistory as : a) {
			result.put("id", as.getId());
			result.put("name", as.getName());
			result.put("sDATE", as.getStartDate());
			result.put("eDATE", as.getEndDate());
			result.put("projectName", p.find(as.getProjectID()).getName());
			result.put("status", as.getStatus());
			result.put("requestedBy", as.getRequestedBy());
			json.add(result);
			result= new JSONObject();
		}
		message.put("item", json);
		
		}
		else {
			message.put("responseData", "True");
			message.put("msg", "No Item");
			message.put("item", json);
			
		}
		return  Arrays.asList(message);
	}
	

}
