package com.assignments.proj.Api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.assignments.proj.Api.dao.AssignmentsFakeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;;

import com.assignments.proj.Api.model.Assignment;

@RestController
@RequestMapping(path = "/assignments")
public class AssignmentsController {

    @Autowired
    private AssignmentsFakeDAO assignmentsDao;

    @GetMapping("")
    public ResponseEntity<JSONObject> getAssignmentsHistoryForEmployee(@RequestParam int employeeId, @RequestParam int pageNumber) {
        /**
         *  should move data processing to appropriate DAOs and only
         *  the controllers return the responses
         */
        List<Assignment> assignments = assignmentsDao.getAssignmentsByUserID(employeeId, pageNumber, 10);

        if (assignments.size() > 0) {
            JSONObject result = new JSONObject();
            result.put("item", assignments);
            result.put("numberOfPage", assignmentsDao.numberOfPages( 10));
            return new ResponseEntity<JSONObject>(result, HttpStatus.OK);
        }
        return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
    }
}
