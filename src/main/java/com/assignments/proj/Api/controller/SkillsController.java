package com.assignments.proj.Api.controller;

import com.assignments.proj.Api.dao.EmployeeDAO;
import com.assignments.proj.Api.dao.SkillDAO;
import com.assignments.proj.Api.model.AssignmentHistory;
import com.assignments.proj.Api.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/skills")
public class SkillsController {
    @Autowired
    private SkillDAO skillsDAO;

    @GetMapping("")
    public ResponseEntity<List<Skill>> getskills() throws SQLException {
        List<Skill> skills = skillsDAO.findAll();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }
}
