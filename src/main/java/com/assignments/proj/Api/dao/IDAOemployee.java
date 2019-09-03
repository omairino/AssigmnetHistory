package com.assignments.proj.Api.dao;

import java.util.List;

import com.assignments.proj.Api.model.Employee;


public interface IDAOemployee extends IDAO<Employee> {

    List<Employee> findByEmployeeName(String name);

    List<Employee> findBySkillName(String name);

    List<Employee> findByIDANDEmployeeName(Integer id, String Employeename);

    List<Employee> findByIDANDSkillName(Integer id, String SkillName);

    List<Employee> findBySkillNameANDEmployeName(String Employeename, String SkillName);

    List<Employee> findByIDANDSkillNameANDEmployeName(Integer id, String Employeename, String SkillName);
//	T findByEmployeeName(String name);

}
