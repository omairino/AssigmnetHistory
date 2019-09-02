package com.assignments.proj.Api.controller;

import java.sql.SQLException;
import java.util.List;

import com.assignments.proj.Api.dao.AssignmentsDAO;
import com.assignments.proj.Api.dao.ProjectsDAO;
import com.assignments.proj.Api.model.Project;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assignments.proj.Api.model.Assignment;

@RestController
@RequestMapping(path = "/getproject")
public class projectController {
    @Autowired
    private ProjectsDAO projectsDAO;
    @GetMapping("")
    public ResponseEntity<List<Project>> getAssignmentsHistoryForEmployee(@RequestParam int managerId) throws SQLException {
        List<Project> projects = projectsDAO.getManagerProjects(managerId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
