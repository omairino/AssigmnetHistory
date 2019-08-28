package com.assignments.proj.Api.dao;

import java.sql.SQLException;
import java.util.List;


public interface IDAO<T> {

    List<T> getAllItems() throws SQLException;

    T insert(T item) throws SQLException;

    T update(T item) throws SQLException;

    T delete(T item) throws SQLException;

    T findByID(int id) throws SQLException;


}
