package com.assignments.proj.Api.dao;
import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.Project;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectsDAO {
    @Autowired
    private DBHandler db;


    public List<Project> getManagerProjects(int managerId) throws SQLException, ResultsNotFoundException {
        List<Project> projectList = new ArrayList<Project>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT id,projectName,description from project where id = ?";

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, managerId);

                try (ResultSet Rs = ps.executeQuery()) {

                    while (Rs.next()) {

                        Project pro = new Project(Rs.getInt(1),  Rs.getString(2),Rs.getString(3));
                        projectList.add(pro);
                    }
                }
            }
        }
        if (projectList.isEmpty()){throw new ResultsNotFoundException("No Projects  Found !! ");}
        return projectList;

    }
    }

