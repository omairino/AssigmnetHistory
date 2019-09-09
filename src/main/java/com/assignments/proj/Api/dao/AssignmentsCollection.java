package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.model.AssignmentHistory;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;

/**
 * specific operations for AssignmentsDao
 */
public interface AssignmentsCollection extends IDAO<Assignment> {
    /**
     * takes the employee id, current page the client is on, the number of item for retrieval
     * and looks for the results accordingly
     * @param id employee id
     * @param currPage current page the client is on
     * @param limit number of elements to be retrieved
     * @return list of assignments
     * @throws SQLException in case of malformed sql queries and database errors
     * @throws ResultsNotFoundException in case there are no results to found
     */
    List<AssignmentHistory> getAssignmentsByUserID(int id, int currPage, int limit) throws SQLException, ResultsNotFoundException;

    /**
     * retrieves the number of pages, used for pagination
     * @param id employee id
     * @param limit how many items should be retrieved
     * @return number of pages for the whole data
     * @throws SQLException in case of malformed sql queries and database errors
     */
    JSONObject numberOfPages(int id, int limit) throws SQLException;
}
