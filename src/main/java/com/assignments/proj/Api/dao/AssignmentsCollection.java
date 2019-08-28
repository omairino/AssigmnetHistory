package com.assignments.proj.Api.dao;

import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;


/**
 * specific operations for AssignmentsDao
 */
public interface AssignmentsCollection<T> extends IDAO<T> {
    JSONObject numberOfPages(int empID, int limit) throws SQLException;
    List<T> getAssignmentsByUserID(int id, int currPage, int limit) throws SQLException;
}
