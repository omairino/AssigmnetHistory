package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectsDAO implements IProjectDAO {
    @Autowired
    private DBHandler db;

    @Override
    public List<Project> findAll() throws SQLException {
        List<Project> projectList = new ArrayList<Project>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT * FROM project";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                try (ResultSet Rs = ps.executeQuery()) {
                    while (Rs.next()) {
                        Project pro = new Project(Rs.getInt(1), Rs.getInt(2), Rs.getString(3), Rs.getString(4), Rs.getDate(5), (List) Arrays.asList(Rs.getArray(6)));
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
    public List<Project> getManagerProjects(int managerId) throws SQLException, ResultsNotFoundException {
        List<Project> projectList = new ArrayList<Project>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT id, projectName, description FROM project where id = ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, managerId);

                try (ResultSet Rs = ps.executeQuery()) {
                    Project pro = null;
                    while (Rs.next()) {
                        pro = new Project(Rs.getInt(1), Rs.getInt(2), Rs.getString(3), Rs.getString(4), Rs.getDate(5), (List) Arrays.asList(Rs.getArray(6)));
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

