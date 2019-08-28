package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.List;


public interface IDAO<T> {
    /**
     * @return list of all available assignments in the database
     * @throws SQLException in case of malformed sql queries and database errors
     * @throws ResultsNotFoundException in case there are no data available
     */
    List<T> getAllItems() throws SQLException;
    /**
     * @param item item to be inserted
     * @return the item with generated id
     * @throws SQLException in case of malformed sql queries and database errors
     */
    T insert(T item) throws SQLException;
    /**
     * @param item item to be updated
     * @return the item with generated id
     * @throws SQLException in case of malformed sql queries and database errors
     */
    T update(T item) throws SQLException;
    /**
     * @param item item to be deleted
     * @return the item that was deleted
     * @throws SQLException in case of malformed sql queries and database errors
     */
    T delete(T item) throws SQLException;

    /**
     * a
     * @param id
     * @return
     * @throws SQLException
     */
    T findByID(int id) throws SQLException;

    // should be in here for global pagination use

    /**
     * used for
     * @param id
     * @param limit
     * @return
     * @throws SQLException
     */
    JSONObject numberOfPages(int id, int limit) throws SQLException;


}
