package com.assignments.proj.Api.controller;

import java.sql.SQLException;
import java.util.List;

import com.assignments.proj.Api.dao.AssignmentsDAO;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.json.simple.JSONObject;
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
    public ResponseEntity<List<Assignment>> getAssignmentsHistoryForEmployee(@RequestParam int employeeId, @RequestParam int pageNumber, @RequestParam int limit) throws SQLException{
        List<Assignment> assignments = assignmentsDao.getAssignmentsByUserID(employeeId, pageNumber,limit);
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Assignment> addAssignment(@RequestBody Assignment assignment) throws SQLException {
        return new ResponseEntity<>(assignmentsDao.insert(assignment), HttpStatus.OK);
    }

    @PostMapping("/update/")
    public ResponseEntity<Assignment> updateAssignment(@RequestBody Assignment assignment) throws SQLException {
        return new ResponseEntity<>(assignmentsDao.update(assignment), HttpStatus.OK);
    }

    @PostMapping("/delete/")
    public ResponseEntity<Assignment> deleteAssignment(@RequestBody Assignment assignment) throws SQLException {
        return new ResponseEntity<>(assignmentsDao.delete(assignment), HttpStatus.OK);
    }

    @GetMapping("/pages")
    public ResponseEntity<JSONObject> getNumverOfPages(@RequestParam int employeeId, @RequestParam int limit) throws SQLException {
        JSONObject jsonObject = assignmentsDao.numberOfPages(employeeId, limit);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
