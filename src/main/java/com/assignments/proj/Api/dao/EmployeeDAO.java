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
        List<Employee> employees = new ArrayList<>();
        List<TechnicalSkill> technicalSkillList = new ArrayList<TechnicalSkill>();
        List<ProductSkill> productSkillList = new ArrayList<ProductSkill>();

        if (pageNumber < 1) {
            pageNumber = 1;
        }
        int offset = (pageNumber - 1) * limit;

        try (Connection conn = db.getConnection()) {
            String employeeQuery = "select u.id, concat(u.first_name, \" \" , u.last_name) as name, u.manager_id " +
                    "as 'manager ID', u.image from users u where manager_id = ?;";
            String technicalSkillQuery = " SELECT s.id, s.name FROM users u join employeeskills es on u.id = " +
                    "es.employeeID join skills s on es.skillid = s.id where type = \"TECHNICAL\" and u.id = ? ";
            String productSkillQuery = "SELECT s.id, s.name FROM users u join employeeskills es on u.id = \" +\n" +
                    "                    \"es.employeeID join skills s on es.skillid = s.id where type = \\\"PRODUCT\\\" and u.id = ?";
//            String sqlCommand = "SELECT u.id, concat(u.first_name , \" \" , u.last_name) as name, u. manager_id, u.image,\n" +
//                    " s.type,  s.name, es.skillid, es.level  from \n" +
//                    "users u join employeeskills es on u.id=es.employeeID join skills s on s.id=es.skillid" +
//                    "where u.employeeid = ? limit ?, offset ?;";

            try (PreparedStatement command = conn.prepareStatement(employeeQuery)) {
                command.setInt(1, managerID);
//                command.setInt(2, limit);
//                command.setInt(3, offset);

                try (ResultSet result = command.executeQuery()) {
                    while (result.next()) {
                        try (PreparedStatement skill = conn.prepareStatement(technicalSkillQuery)) {
                            // hon mngdrsh nhot bdl result.getInt  -> managerID w5ls?
                            skill.setInt(1, result.getInt("u.id"));

                            try (ResultSet tsSkill = skill.executeQuery()) {
                                while (tsSkill.next()) {
                                    TechnicalSkill technicalSkill = new TechnicalSkill(tsSkill.getInt(1), tsSkill.getString(2), 0);
                                    technicalSkillList.add(technicalSkill);
                                }
                            }
                        }
                        try (PreparedStatement skill = conn.prepareStatement(productSkillQuery)) {
                            skill.setInt(1, result.getInt("u.id"));

                            try (ResultSet psSkill = skill.executeQuery()) {
                                while (psskill.next()) {
                                    ProductSkill productSkill = new ProductSkill(psSkill.getInt(1), psSkill.getString(2), 0);
                                    productSkillList.add(productSkill);
                                }
                            }
                        }

                        Employee employee = new Employee(result.getInt("u.id"),
                                result.getInt("u.manager_id"),
                                result.getString("u.name"),
                                technicalSkillList, productSkillList,
                                result.getString("u.image"));
                        employees.add(employee);
                        // hay 3mlnaha 3shan njdd llemp aljded?
                        technicalSkillList = new ArrayList<TechnicalSkill>();
                        productSkillList = new ArrayList<ProductSkill>();

                    }
                }
            }

        }


        return employees;
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
