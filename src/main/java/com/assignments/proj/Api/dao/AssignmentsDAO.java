package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentsDAO implements AssignmentsCollection<Assignment> {

    @Autowired
    private DBHandler db;
    // this class is a singleton so you can't save the list as a member
    // already retried because other requests will get a duplicate results because od
    // loading in entries in the list for every request
    //private List<Assignment> assignments;

    @Override
    public JSONObject numberOfPages(int empID, int limitPage) throws SQLException {
        final String NUMBEROFPAGES = "numberOfPage";

        // query retrieves the number of entries in a table
        // COUNT(ID) can be changed in the future according to what id to be used
        String query = "SELECT COUNT(ID) FROM assignments";
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
        if (rowCount % limitPage == 0){
            result.put(NUMBEROFPAGES, (rowCount / limitPage));
            return result;
        }
        numOfPages = (int) Math.floor(rowCount / limitPage) + 1;
        result.put(NUMBEROFPAGES,numOfPages);
        return result;

    }


    @Override
    public List<Assignment> getAssignmentsByUserID(int id, int currPage, int limit) throws SQLException, ResultsNotFoundException {
        List<Assignment> assignments = new ArrayList<>();

        if (currPage < 1)
            currPage = 1;

        int offset = (currPage - 1) * limit; // index of which row to start retrieving data

        try (Connection conn = db.getConnection()) {
            String sqlCommand = "Select a.id,projectName,assignmentName,startDate,endDate,status,requestedBy from project p join assignmenthistory a "
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
        if (assignments.isEmpty())
            throw new ResultsNotFoundException("Couldn't find assignments for this employee");

        return assignments;
    }

    /*
    NOTE: we only need the response of number of pages to be wrapped in json because
          when returning the number of pages the the spring framework prints it out as a number without
          json, but spring can convert the other objects to json by itself
    @Override
    public List<JSONObject> jsonResult() {
        JSONObject result = new JSONObject();
        result.put("numberOfPage", this.numberOfPages(10));
        result.put("item", this.assignments);

        return Arrays.asList(result);
    }
*/


    @Override
    public List<Assignment> getAllItems() throws SQLException, ResultsNotFoundException {
        List<Assignment> assignments = new ArrayList<>();

        try (Connection conn = db.getConnection()) {
            String sqlCommand = "Select a.id,projectName,assignmentName,startDate,endDate,status,requestedBy from project p join assignmenthistory a "
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


    @Override
    public Assignment insert(Assignment item) throws SQLException{

        try (Connection conn = db.getConnection()) {
            //TODO complete query
            String sqlCommand = "insert query";

            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {
                //command.execute();
                // try (ResultSet result = ) {
                //}
                //TODO if insert successful
                // return object
            }
        }
        return null;
    }

    @Override
    public Assignment update(Assignment item) throws SQLException{
        try (Connection conn = db.getConnection()) {
            //TODO complete query
            String sqlCommand = "update query";

            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {
                //command.execute();
                // try (ResultSet result = ) {
                //}
                //TODO if update successful
                // return object
            }
        }
        return null;
    }

    @Override
    public Assignment delete(Assignment item) throws SQLException{
        try (Connection conn = db.getConnection()) {
            //TODO complete query
            String sqlCommand = "delete query";

            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {
                //command.execute();
                // try (ResultSet result = ) {
                //}
                //TODO if delete successful
                // return object
            }
        }
        return null;
    }

    @Override
    public Assignment findByID(int id) throws SQLException, ResultsNotFoundException {
        Assignment assignment = null;
        try (Connection conn = db.getConnection()) {
            String sqlCommand = "Select a.id,projectName,assignmentName,startDate,endDate,status,requestedBy from project p join assignmenthistory a "
                    + " on p.id = a.projectid"
                    + " where a.id = ?";

            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {
                command.setInt(1, id);

                try (ResultSet result = command.executeQuery()) {
                    if (result.next()) {
                        assignment= new Assignment(result.getInt("a.id"),
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
