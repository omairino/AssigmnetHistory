package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.model.Employee;
import com.assignments.proj.Api.model.Skill;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO extends IDAO<Employee> {
    public List<Employee> getEmployeesByManagerID (int managerID,int pageNumber,int limit) throws SQLException;
    public List<Employee> getEmployeesByProjectID(int projectid) throws SQLException;
    public List<Employee> searchEmployeesBySkillID(int skillID, int pageNumber, int limit) throws SQLException;
    public List<Employee> searchEmployeesBySkillSet(List<Integer> skillSet, int pageNumber, int limit) throws SQLException;

}
