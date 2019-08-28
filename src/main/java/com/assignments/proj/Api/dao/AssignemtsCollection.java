package com.assignments.proj.Api.dao;


import com.fasterxml.jackson.annotation.JsonAlias;
import org.json.simple.JSONObject;

import java.util.List;


/**
 * specific operations for AssignmentsDao
 */
public interface AssignemtsCollection<T> extends IDAO<T> {
    int numberOfPages(int limit);
    List<T> getAssignmentsByUserID(int id, int currPage, int limit);
    List<JSONObject> jsonResult();
}
