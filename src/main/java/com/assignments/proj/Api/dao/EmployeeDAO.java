package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.model.Employee;
import com.assignments.proj.Api.model.ProductSkill;
import com.assignments.proj.Api.model.TechnicalSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeDAO implements IEmployeeDAO {
    @Autowired
    private DBHandler db;
    @Override
    public List<Employee> getEmployeesByManagerID(int managerID, int pageNumber, int limit) throws SQLException {
        List<Employee> employees=new ArrayList<>();
         if(pageNumber<1) {
             pageNumber = 1;
         }
         int offset=(pageNumber-1)*limit;

        try (Connection conn = db.getConnection()) {
            String sqlCommand = "SELECT u.id, concat(u.first_name , \" \" , u.last_name) as name, u. manager_id, u.image,\n" +
                    " s.type,  s.name, es.skillid, es.level  from \n" +
                    "users u join employeeskills es on u.id=es.employeeID join skills s on s.id=es.skillid" +
                    "where u.employeeid = ? limit ?,?;";

            try (PreparedStatement command = conn.prepareStatement(sqlCommand)) {
                command.setInt(1, managerID);
                command.setInt(2, offset);
                command.setInt(3, limit);

                try (ResultSet result = command.executeQuery()) {

//                    ProductSkill p = new ProductSkill();
                    while (result.next()) {
                        if(result.getString("s.type").equals("Technical")){
                            TechnicalSkill t= new TechnicalSkill(
                                    result.getInt("s.skillid"),
                                    result.getString("s.name"),
                                    result.getInt("s.level")
                            );
                        }
                        else
                        employees.add(new Employee(result.getInt("u.id"),
                                result.getString("u.name"),
                                result.getInt("u.manager_id"),
                                result.getString("u.image"),t,p)
                        );
                    }
                }
            }

        }


        return null;
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        return null;
    }

    @Override
    public Employee add(Employee item) throws SQLException {
        return null;
    }

    @Override
    public Employee update(Employee item) throws SQLException {
        return null;
    }

    @Override
    public Employee delete(Employee item) throws SQLException {
        return null;
    }

    @Override
    public Employee find(int id) throws SQLException {
        return null;
    }
}
