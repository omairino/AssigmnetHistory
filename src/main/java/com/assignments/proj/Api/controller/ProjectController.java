package com.assignments.proj.Api.controller;

import java.sql.SQLException;
import java.util.List;


import com.assignments.proj.Api.dao.IProjectDAO;
import com.assignments.proj.Api.dao.ProjectsDAO;
import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    @Autowired
    private IProjectDAO projectsDAO;
    @Autowired
    private ProjectsDAO addProjectDAO;

    @GetMapping("")
    public ResponseEntity<List<Project>> getProjects() throws SQLException {
        return new ResponseEntity<>(projectsDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{managerID}")
    public ResponseEntity<List<Project>> getProjectsByID(@PathVariable("managerID") int managerID) throws SQLException {
        return new ResponseEntity<>(projectsDAO.getManagerProjects(managerID), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Project> addProject(@RequestBody Project project) throws SQLException {
        return new ResponseEntity<>(addProjectDAO.add(project), HttpStatus.OK);
    }
}
