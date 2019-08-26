package com.assignments.proj.Api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.assignments.proj.Api.model.Assignment;
import org.springframework.web.bind.annotation.*;

import com.assignments.proj.Api.model.AssignmentHistory;

@RestController
@RequestMapping(path = "/assignments")
public class AssignmentsController {


	@GetMapping()
	public List<Assignment> getAllAssignments(){

		System.out.println("raaaaaan");

		List<Assignment> arr = new ArrayList<>();
		arr.add(
				new Assignment(1,1,1, "sad", new Date(), new Date(), "sad","asdsd"));
		return arr;
	}


	@GetMapping("/{id}")
	public @ResponseBody List<Assignment> getAssignmentsHistoryForEmployee(@PathVariable int id){

		System.out.println("raaaaaan");

		List<Assignment> arr = new ArrayList<>();
		arr.add(
				new Assignment(1,1,1, "sad", new Date(), new Date(), "sad","asdsd"));
		return arr;
	}

	@PostMapping("/")
	public AssignmentHistory insertAssignment(Assignment asn){
		return null;
	}

}
