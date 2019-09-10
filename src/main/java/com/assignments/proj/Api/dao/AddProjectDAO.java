package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.model.AddProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class AddProjectDAO implements IDAO<AddProject> {
    @Autowired
    private DBHandler db;

    @Override
    public List<AddProject> findAll() throws SQLException {
        return null;
    }

    @Override
    public AddProject add(AddProject item) throws SQLException {
        int projectID;
        try (Connection conn = db.getConnection()) {

            String insertQueryProject = "INSERT INTO project (name, manager_id, description,start_date)" +
                    "VALUES (?,?,?,?)";
            try (PreparedStatement fetch = conn.prepareStatement(insertQueryProject, Statement.RETURN_GENERATED_KEYS)) {
                fetch.setString(1, item.getProjectName());
                fetch.setString(2, String.valueOf(item.getManagerID()));
                fetch.setString(3, item.getDescription());
                fetch.setString(4, String.valueOf(item.getStartDate()));
                fetch.executeUpdate();
                try (ResultSet generatedID = fetch.getGeneratedKeys()) {
                    if (generatedID.next())
                        projectID = generatedID.getInt(1);

                    else
                        throw new SQLException("Project insertion failed.");
                }
            }

            StringBuilder insertProjectSkill = new StringBuilder("INSERT INTO projectsSkills (projectID, SkillID,level)\n" +
                    " VALUES (?, ?,?)");
            int sizeSkill = item.getSkills().size();
            for (int i = 0; i < sizeSkill - 1; i++) {
                insertProjectSkill.append(", (?, ?, ?)");
            }

            try (PreparedStatement fetch = conn.prepareStatement(String.valueOf(insertProjectSkill), Statement.RETURN_GENERATED_KEYS)) {
                int counter = 0;
                for (int i = 1; i <= sizeSkill*3 ; i += 3) {
                    fetch.setString(i, String.valueOf(projectID));
                    fetch.setString(i + 1, String.valueOf(item.getSkills().get(counter).getId()));
                    fetch.setString(i + 2, String.valueOf(item.getSkills().get(counter).getLevel()));
                    ++counter;
                }
                fetch.executeUpdate();
            }
        }

        return item;
    }

    @Override
    public AddProject update(AddProject item) throws SQLException {
        return null;
    }

    @Override
    public AddProject delete(AddProject item) throws SQLException {
        return null;
    }

    @Override
    public AddProject find(int id) throws SQLException {
        return null;
    }
}
