package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.Project;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectsDAO implements IDAO<Project>{
    @Autowired
    private DBHandler db;

    @Override
    public List<Project> getAllItems() throws SQLException {
        List<Project> projectList = new ArrayList<Project>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT * FROM project";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                try (ResultSet Rs = ps.executeQuery()) {
                    while (Rs.next()) {
                        Project pro = new Project(Rs.getInt(1), Rs.getString(2), Rs.getString(3));
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

    /*public List<Project> getManagerProjects(int managerId) throws SQLException, ResultsNotFoundException {
        List<Project> projectList = new ArrayList<Project>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT id, projectName, description FROM project where id = ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, managerId);

                try (ResultSet Rs = ps.executeQuery()) {

                    while (Rs.next()) {

                        Project pro = new Project(Rs.getInt(1), Rs.getString(2), Rs.getString(3));
                        projectList.add(pro);
                    }
                }
            }
        }

        if (projectList.isEmpty()) {
            throw new ResultsNotFoundException("No Projects  Found !! ");
        }
        return projectList;
    }*/

    @Override
    public Project insert(Project item) throws SQLException {
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
    public Project findByID(int id) throws SQLException {
        return null;
    }

    @Override
    public JSONObject numberOfPages(int id, int limit) throws SQLException {
        return null;
    }
}

