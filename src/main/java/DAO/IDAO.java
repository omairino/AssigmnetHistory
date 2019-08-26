package DAO;

import java.sql.SQLException;
import java.util.List;

<<<<<<< HEAD


public interface IDAO<T>{

    List<T> getAllItems();
    T insert(T item);
    T update(T item);
    T delete(T item);
    int numberOfPages(int id, int limit);
=======
public interface IDAO<T> {
	List<T> getAllItems();
	T insert(T item);
	T update(T item);
	T delete(T item);
	int numberOfPages(int id, int limit);
>>>>>>> amjad
}
