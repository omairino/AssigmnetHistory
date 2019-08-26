package com.assignments.proj.Api.DAO;


import java.util.List;


/**
 * specific operations for AssignmentsDao
 */
public interface AssignemtsCollection<T> extends IDAO<T> {
	int numberOfPages(int id, int limit);
    List<T> getAssignmentsByUserID(int id, int currPage, int limit);
}
