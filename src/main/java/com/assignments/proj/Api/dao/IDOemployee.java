package com.assignments.proj.Api.dao;
import java.util.List;
import com.assignments.proj.Api.model.Employee;


public interface IDOemployee<T> {
	
	List<T> getAllemployees();
	T findByID(int id);
//	T findByEmployeeName(String name);

}
