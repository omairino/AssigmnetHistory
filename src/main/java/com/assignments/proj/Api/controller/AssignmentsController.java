package com.assignments.proj.Api.controller;

import java.sql.SQLException;
import java.util.List;

import com.assignments.proj.Api.dao.AssignmentsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assignments.proj.Api.model.Assignment;

@RestController
@RequestMapping(path = "/assignments")
public class AssignmentsController {

    @Autowired
    private AssignmentsDAO assignmentsDao;

    @GetMapping("")
    public ResponseEntity<List<Assignment>> getAssignmentsHistoryForEmployee(@RequestParam int employeeId, @RequestParam int pageNumber, @RequestParam int limit) throws SQLException {
        List<Assignment> assignments = assignmentsDao.getAssignmentsByUserID(employeeId, pageNumber,limit);
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }
}
