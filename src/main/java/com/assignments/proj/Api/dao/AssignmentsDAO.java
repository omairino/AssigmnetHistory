package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.model.Assignment;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentsDAO implements AssignemtsCollection<Assignment> {
    @Autowired
    private DBHandler db;
    private List<Assignment> assignments = Arrays.asList();

    @Override
    public int numberOfPages(int limitPage) {
        List<Assignment> tempAsns = Optional.of(assignments
                .stream()
//                .filter(Assignment -> Assignment.getEmployeeID() == id)
                .collect(Collectors.toList())).orElse(null);

        if (tempAsns.size() == 0)
            return 0;
        // if number of items dives into exact number
        // return it without any further calculation
        if (tempAsns.size() % limitPage == 0)
            return tempAsns.size() / limitPage;


        return (int) Math.floor(tempAsns.size() / limitPage) + 1;

    }

    @Override
    public List getAssignmentsByUserID(int id, int currPage, int limit) throws SQLException {

        try (Connection conn = db.getConnection()) {


            String sqlCommand = "Select id,projectName,assignmentName,startDate,endDate,status,requestedBy from Project p join AssignmentHistory a "
                    + " on p.id = a.projectid"
                    + " where a.employeeid = ?";


            PreparedStatement command = conn.prepareStatement(sqlCommand);
            command.setString(1, String.valueOf(id));
            ResultSet result = command.executeQuery();
            while (result.next()) {
                assignments.add(new Assignment(result.getInt("id"),
                        result.getString("projectName"),
                        result.getString("assignmentName"),
                        result.getDate("startDate"),
                        result.getDate("endDate"),
                        result.getString("status"),
                        result.getString("requestedBy"))
                );
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return assignments;
    }


    @Override
    public List<JSONObject> jsonResult() {
        JSONObject result = new JSONObject();
        result.put("numberOfPage", this.numberOfPages(10));
        result.put("item", this.assignments);

        return Arrays.asList(result);
    }

    @Override
    public List getAllItems() {
        return assignments;
    }

    @Override
    public Assignment insert(Assignment item) {
        return null;
    }

    @Override
    public Assignment update(Assignment item) {
        return null;
    }

    @Override
    public Assignment delete(Assignment item) {
        return null;
    }

    @Override
    public Assignment find(int id) {
        for (Assignment p : this.assignments) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

}
