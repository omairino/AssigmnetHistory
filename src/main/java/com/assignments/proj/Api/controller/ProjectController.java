package com.assignments.proj.Api.controller;

import java.sql.SQLException;
import java.util.List;

import com.assignments.proj.Api.dao.AssignmentsDAO;
import com.assignments.proj.Api.dao.ProjectsDAO;
import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.Project;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assignments.proj.Api.model.Assignment;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    @Autowired
    private ProjectsDAO projectsDAO;

    @GetMapping("")
    public ResponseEntity<List<Project>> getAssignmentsHistoryForEmployee() throws SQLException {
        return new ResponseEntity<>(projectsDAO.getAllItems(), HttpStatus.OK);
    }
}
