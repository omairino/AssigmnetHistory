package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.Project;
import com.assignments.proj.Api.model.ProjectAndSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectsDAO implements IProjectDAO {
    @Autowired
    private DBHandler db;

    @Override
    public List<Project> findAll() throws SQLException {
        List<ProjectAndSkill> projectSkillList = new ArrayList<ProjectAndSkill>();
        List<Project> projectList = new ArrayList<Project>();
        List<Integer> skillproject = new ArrayList<Integer>();
        try (Connection conn = db.getConnection()) {
            String query = "select id,managerid,projectName, description,startdate,skillid from project p join projectsskills s on p.id = s.projectid";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                try (ResultSet Rs = ps.executeQuery()) {
                    while (Rs.next()) {
                        ProjectAndSkill pro = new ProjectAndSkill(Rs.getInt(1), Rs.getInt(6));
                        projectSkillList.add(pro);
                        if(!skillproject.contains(Rs.getInt(1))){
                            skillproject.add(Rs.getInt(1));
                         Project pro2 = new Project(Rs.getInt(1), Rs.getInt(2), Rs.getString(3), Rs.getString(4), Rs.getDate(5));
                         projectList.add(pro2);}
                    }
                }
            }
        }

        Map<Integer, List<Integer>> collect = projectSkillList.stream()
                .collect(Collectors.groupingBy(ProjectAndSkill::getProjectID, Collectors.mapping(p -> p.getSkillID(), Collectors.toList())));
        int counter = 0;
        for (Integer skill : collect.keySet()) {
            projectList.get(counter).setSkills(collect.get(skill));
            counter += 1;
        }

        if (projectList.isEmpty()) {
            throw new ResultsNotFoundException("No Projects  Found!! ");
        }
        return projectList;
    }

    @Override
    public List<Project> getManagerProjects(int managerId) throws SQLException, ResultsNotFoundException {
        List<Project> projectList = new ArrayList<Project>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT id, projectName, description FROM project where id = ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, managerId);

                try (ResultSet Rs = ps.executeQuery()) {
                    Project pro = null;
                    while (Rs.next()) {
                        pro = new Project(Rs.getInt(1), Rs.getInt(2), Rs.getString(3), Rs.getString(4), Rs.getDate(5));
                        projectList.add(pro);
                    }
                }
            }
        }

        if (projectList.isEmpty()) {
            throw new ResultsNotFoundException("No Projects  Found !! ");
        }
        return projectList;
    }

    @Override
    public Project add(Project item) throws SQLException {
        return null;
    }

    @Override
    public Project update(Project item) throws SQLException {
        return null;
    }

    @Override
    public Project delete(Project item) throws SQLException {
        return null;
    }

    @Override
    public Project find(int id) throws SQLException {
        return null;
    }

}

