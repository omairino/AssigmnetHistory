package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.BadRequestException;
import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.AssignmentHistory;
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
        String query = "SELECT COUNT(id) FROM assignment";
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
    public List<AssignmentHistory> getAssignmentsByUserID(int id, int currPage, int limit) throws SQLException {
        List<AssignmentHistory> assignments = new ArrayList<AssignmentHistory>();

        if (currPage < 1)
            currPage = 1;

        int offset = (currPage - 1) * limit; // index of which row to start retrieving data

        try (Connection conn = db.getConnection()) {
//            String sqlCommand = "Select a.id,a.project_id,p.name,a.start_date,a.end_date,a.status,a.requested_from_manager_id" +
//                    " from project p join assignment a \n"
//                    + " on p.id = a.project_id"
//                    + " where a.employee_id = ? limit ? offset ?";

            String sqlCommand = "Select a.id, project_id, p.name, a.start_date, a.end_date, a.status, a.requested_from_manager_id\n" +
                    "                  from assignment a join project p on a.project_id=p.id where employee_id = ? limit ? offset ?";

            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {
                command.setInt(1, id);
                command.setInt(2, limit);
                command.setInt(3, offset);

                try (ResultSet result = command.executeQuery()) {
                    while (result.next()) {
                        assignments.add(new AssignmentHistory(
                                result.getInt("a.id"),
                                result.getInt("a.project_id"),
                                result.getString("p.name"),
                                result.getDate("a.start_date"),
                                result.getDate("a.end_date"),
                                result.getInt("a.requested_from_manager_id"),
                                result.getString("a.status"))
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
        List<AssignmentHistory> assignmentsHistory = new ArrayList<>();


        try (Connection conn = db.getConnection()) {
            String sqlCommand = "Select a.id, a.project_id, a.name, a.start_date, a.end_date, a.requested_from_manager_id, status" +
                    "from project p inner join assignment a "
                    + " on p.id = a.project_id";
            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {

                try (ResultSet result = command.executeQuery()) {
                    while (result.next()) {
                        assignmentsHistory.add(new AssignmentHistory(result.getInt("a.id"),
                                result.getInt("a.project_id"),
                                result.getString("a.name"),
                                result.getDate("a.start_date"),
                                result.getDate("a.end_date"),
                                result.getInt("a.requested_from_manager_id"),
                                result.getString("status")));
                    }
                }
            }

        }
        if (assignmentsHistory.isEmpty())
            throw new ResultsNotFoundException("Couldn't find assignments History");

        return null;
    }


    @Override
    public List<Assignment> findAll() throws SQLException {
        List<Assignment> assignmentsList = new ArrayList<Assignment>();

        try (Connection conn = db.getConnection()) {
            String assignmentQuery = "SELECT a.id, a.project_id, a.employee_id, a.start_date, " +
                    "a.end_date, a.request_from_manager_id, a.request_to_manager_id, a.status FROM assignment a";

            try (PreparedStatement command = conn.prepareStatement(assignmentQuery)) {

                try (ResultSet result = command.executeQuery()) {
                    while (result.next()) {
                        assignmentsList.add(new Assignment(result.getInt("a.id"),
                                result.getInt("a.project_id"),
                                result.getInt("a.employee_id"),
                                result.getDate("a.start_date"),
                                result.getDate("a.end_date"),
                                result.getInt("a.requested_from_manager_id"),
                                result.getInt("a.requested_to_manager_id"),
                                result.getString("a.status")));

                    }
                }
            }

        }
        if (assignmentsList.isEmpty())
            throw new ResultsNotFoundException("Couldn't find assignments List");

        return null;
    }

    @Override
    public Assignment add(Assignment item) throws SQLException {
        System.out.println(item.getEmployeeID());
        try (Connection conn = db.getConnection()) {
            // fetch project id by name since project is a unique name which
            // guarantees retrieving the appropriate id
            String insertQuery = "INSERT INTO assignment (project_id, employee_id, start_date, end_date, requested_from_manager_id," +
                    " requested_to_manager_id, status) VALUES(?, ?, ?, ?, ?, ?,?)";

            // preparing a statement that guarantees returning the auto generated id
            try (PreparedStatement command = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                command.setInt(1, item.getProjectID());
                command.setInt(2, item.getEmployeeID());
                command.setDate(3, item.getStartDate());
                command.setDate(4, item.getEndDate());
                command.setInt(5, item.getRequestFromManagerID());
                command.setInt(6, item.getRequestToManagerID());
                command.setString(7, item.getStatus());
                command.executeUpdate();
                try (ResultSet generatedID = command.getGeneratedKeys()) {
                    if (generatedID.next())
                        item.setId(generatedID.getInt(1));
                    else
                        throw new SQLException("Assignment insertion failed.");
                }
            }


        }
        return item;
    }


    @Override
    public Assignment update(Assignment item) throws SQLException {
        return null;
    }

    @Override
    public Assignment delete(Assignment item) throws SQLException {
        return null;
    }

    // try (Connection conn = db.getConnection()) {
    //     String updateQuery = "UPDATE assignment SET startDate = ?, endDate = ?, status = ?, requestedBy = ?;";

    //   try (PreparedStatement command = conn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
    //   command.setDate(1, item.getStartDate());
    //  command.setDate(2, item.getEndDate());
    // command.setString(3, item.getStatus());
//                command.setString(4, item.getRequestedBy());
//
//                command.executeUpdate();
//                try (ResultSet generatedID = command.getGeneratedKeys()) {
//                    if (generatedID.next())
//                        item.setId(generatedID.getInt(1));
//                    else
//                        throw new SQLException("Assignment update failed.");
//                }
//            }
//        }
    //    return item;
    //  }

//    @Override
//    public Assignment delete(Assignment item) throws SQLException {
//        try (Connection conn = db.getConnection()) {
//            String deleteQuery = "DELETE FROM assignmenthistory WHERE id = ?";
//
//            try (PreparedStatement command = conn.prepareStatement(deleteQuery)) {
//                //command.execute();
//                if (command.executeUpdate() > 0) {
//                    return item;
//                }
//            }
//        }
//        throw new SQLException("Couldn't delete query");
//    }

    @Override
    public Assignment find(int id) throws SQLException, ResultsNotFoundException {
//        Assignment assignment = null;
//        try (Connection conn = db.getConnection()) {
//            String sqlCommand = "Select a.id,projectName,assignmentName,startDate,endDate,status,requestedBy from project p inner join assignmenthistory a "
//                    + " on p.id = a.projectid"
//                    + " where a.id = ?";
//
//            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {
//                command.setInt(1, id);
//
//                try (ResultSet result = command.executeQuery()) {
//                    if (result.next()) {
//                        assignment = new Assignment(result.getInt("a.id"),
//                                result.getString("projectName"),
//                                result.getString("assignmentName"),
//                                result.getDate("startDate"),
//                                result.getDate("endDate"),
//                                result.getString("status"),
//                                result.getString("requestedBy"));
//                    }
//                }
//            }
//        }
//
//        if (assignment == null)
//            throw new ResultsNotFoundException("Couldn't find requested assignment");
//
//        return assignment;
        return null;
    }
}

