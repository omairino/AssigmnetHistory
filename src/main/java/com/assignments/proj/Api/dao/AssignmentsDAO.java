package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.BadRequestException;
import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentsDAO implements AssignmentsCollection {

    @Autowired
    private DBHandler db;

    @Override
    public JSONObject numberOfPages(int empID, int limitPage) throws SQLException {
        final String NUMBEROFPAGES = "numberOfPage";

        // query retrieves the number of entries in a table
        // COUNT(ID) can be changed in the future according to what id to be used
        String query = "SELECT COUNT(id) FROM assignmenthistory";
        int rowCount = 0;
        Integer numOfPages = 0;
        JSONObject result = new JSONObject();
        result.put(NUMBEROFPAGES, 0);

        try (Connection conn = db.getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        rowCount = resultSet.getInt(1);
                    }
                }
            }
        }

        if (rowCount == 0)
            return result;
        // if number of items dives into exact number
        // return it without any further calculation
        if (rowCount % limitPage == 0) {
            result.put(NUMBEROFPAGES, (rowCount / limitPage));
            return result;
        }
        numOfPages = (int) Math.floor(rowCount / limitPage) + 1;
        result.put(NUMBEROFPAGES, numOfPages);
        return result;

    }

    @Override
    public List<Assignment> getAssignmentsByUserID(int id, int currPage, int limit) throws SQLException {
        List<Assignment> assignments = new ArrayList<Assignment>();

        if (currPage < 1)
            currPage = 1;

        int offset = (currPage - 1) * limit; // index of which row to start retrieving data

        try (Connection conn = db.getConnection()) {
            String sqlCommand = "Select a.id,projectName,assignmentName,startDate,endDate,status,requestedBy from project p inner join assignmenthistory a "
                    + " on p.id = a.projectid"
                    + " where a.employeeid = ? limit ?,?";

            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {
                command.setInt(1, id);
                command.setInt(2, offset);
                command.setInt(3, limit);

                try (ResultSet result = command.executeQuery()) {
                    while (result.next()) {
                        assignments.add(new Assignment(result.getInt("a.id"),
                                result.getString("projectName"),
                                result.getString("assignmentName"),
                                result.getDate("startDate"),
                                result.getDate("endDate"),
                                result.getString("status"),
                                result.getString("requestedBy"))
                        );
                    }
                }
            }

        }
        if (assignments.isEmpty()) {
            throw new ResultsNotFoundException("Couldn't find assignments for this employee");

        }
        return assignments;
    }


    public List<Assignment> getAllItems() throws SQLException, ResultsNotFoundException {
        List<Assignment> assignments = new ArrayList<>();

        try (Connection conn = db.getConnection()) {
            String sqlCommand = "Select a.id,projectName,assignmentName,startDate,endDate,status,requestedBy " +
                    "from project p inner join assignmenthistory a "
                    + " on p.id = a.projectid";

            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {

                try (ResultSet result = command.executeQuery()) {
                    while (result.next()) {
                        assignments.add(new Assignment(result.getInt("a.id"),
                                result.getString("projectName"),
                                result.getString("assignmentName"),
                                result.getDate("startDate"),
                                result.getDate("endDate"),
                                result.getString("status"),
                                result.getString("requestedBy"))
                        );
                    }
                }
            }

        }
        if (assignments.isEmpty())
            throw new ResultsNotFoundException("Couldn't find any assignment");

        return assignments;
    }


    public Assignment insert(Assignment item) throws SQLException {

        try (Connection conn = db.getConnection()) {
            // fetch project id by name since project is a unique name which
            // guarantees retrieving the appropriate id
            String projectQuery = "SELECT id FROM project WHERE projectName = ?";
            String insertQuery = "INSERT INTO assignmenthistory (projectID, assignmentName, startDate, endDate, status, requestedBy) VALUES(?, ?, ?, ?, ?, ?)";
            try (PreparedStatement fetch = conn.prepareStatement(projectQuery)) {
                fetch.setString(1, item.getProjectName());
                try (ResultSet resultSet = fetch.executeQuery()) {

                    if (resultSet.next()) {
                        int projectID = resultSet.getInt(1);
                        // preparing a statement that guarantees returning the auto generated id
                        try (PreparedStatement command = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                            command.setInt(1, projectID);
                            command.setString(2, item.getAssignmentName());
                            command.setDate(3, item.getStartDate());
                            command.setDate(4, item.getEndDate());
                            command.setString(5, item.getStatus());
                            command.setString(6, item.getRequestedBy());

                            command.executeUpdate();
                            try (ResultSet generatedID = command.getGeneratedKeys()) {
                                if (generatedID.next())
                                    item.setId(generatedID.getInt(1));
                                else
                                    throw new SQLException("Assignment insertion failed.");
                            }
                        }
                    } else
                        throw new BadRequestException("Failed attempt to add an assignment to a non-existent project.");
                }
            }
        }
        return item;
    }

    @Override
    public List<Assignment> findAll() throws SQLException {
        return null;
    }

    @Override
    public Assignment add(Assignment item) throws SQLException {
        return null;
    }

    @Override
    public Assignment update(Assignment item) throws SQLException {
        try (Connection conn = db.getConnection()) {
            String updateQuery = "UPDATE assignmenthistory SET startDate = ?, endDate = ?, status = ?, requestedBy = ?;";
            // preparing a statement that guarantees returning the auto generated id
            try (PreparedStatement command = conn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {

                command.setDate(1, item.getStartDate());
                command.setDate(2, item.getEndDate());
                command.setString(3, item.getStatus());
                command.setString(4, item.getRequestedBy());

                command.executeUpdate();
                try (ResultSet generatedID = command.getGeneratedKeys()) {
                    if (generatedID.next())
                        item.setId(generatedID.getInt(1));
                    else
                        throw new SQLException("Assignment update failed.");
                }
            }
        }
        return item;
    }

    @Override
    public Assignment delete(Assignment item) throws SQLException {
        try (Connection conn = db.getConnection()) {
            String deleteQuery = "DELETE FROM assignmenthistory WHERE id = ?";

            try (PreparedStatement command = conn.prepareStatement(deleteQuery)) {
                //command.execute();
                if (command.executeUpdate() > 0) {
                    return item;
                }
            }
        }
        throw new SQLException("Couldn't delete query");
    }

    @Override
    public Assignment find(int id) throws SQLException, ResultsNotFoundException {
        Assignment assignment = null;
        try (Connection conn = db.getConnection()) {
            String sqlCommand = "Select a.id,projectName,assignmentName,startDate,endDate,status,requestedBy from project p inner join assignmenthistory a "
                    + " on p.id = a.projectid"
                    + " where a.id = ?";

            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {
                command.setInt(1, id);

                try (ResultSet result = command.executeQuery()) {
                    if (result.next()) {
                        assignment = new Assignment(result.getInt("a.id"),
                                result.getString("projectName"),
                                result.getString("assignmentName"),
                                result.getDate("startDate"),
                                result.getDate("endDate"),
                                result.getString("status"),
                                result.getString("requestedBy"));
                    }
                }
            }
        }

        if (assignment == null)
            throw new ResultsNotFoundException("Couldn't find requested assignment");

        return assignment;
    }
}
