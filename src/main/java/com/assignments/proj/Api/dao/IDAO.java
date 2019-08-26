package com.assignments.proj.Api.DAO;

import java.util.List;


public interface IDAO<T>{

    List<T> getAllItems();
    T insert(T item);
    T update(T item);
    T delete(T item);
    int numberOfPages(int id, int limit);

}
