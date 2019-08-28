package com.assignments.proj.Api.dao;


import java.util.List;


/**
 * specific operations for AssignmentsDao
 */
public interface AssignemtsCollection<T> extends IDAO<T> {
	int numberOfPages( int limit);
    List<T> getAssignmentsByUserID(int id, int currPage, int limit);
}
