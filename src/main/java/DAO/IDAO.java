package DAO;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
	List<T> findAll() throws SQLException;
	T find(int id) throws SQLException;
	T add(T item) throws SQLException;
	T update(T item) throws SQLException;
	T delete(int item) throws SQLException;
}
