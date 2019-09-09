package com.assignments.proj.Api.controller;

import com.assignments.proj.Api.dao.EmployeeDAO;
import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/myteam")
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping("")
    public ResponseEntity<List<Employee>> getEmployeesHistoryForEmployee(@RequestParam int managerID, @RequestParam int pageNumber, @RequestParam int limit) throws SQLException {
        List<Employee> employees = employeeDAO.getEmployeesByManagerID(managerID, pageNumber,limit);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
