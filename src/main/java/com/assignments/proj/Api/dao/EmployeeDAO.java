package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.model.Employee;
import com.assignments.proj.Api.model.ProductSkill;
import com.assignments.proj.Api.model.TechnicalSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
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
                    ", u.image from users u where manager_id = ? limit ? offset ?;";
            String technicalSkillQuery = " SELECT s.id, s.name,es.level FROM users u join employeeskill es on u.id = " +
                    "es.user_id join skills s on es.skill_id = s.id where type = \"TECHNICAL\" and u.id = ? ";
            String productSkillQuery = "SELECT s.id, s.name,es.level FROM users u join employeeskill es on u.id = \" +\n" +
                    "\"es.user_id join skills s on es.skill_id = s.id where type = \\\"PRODUCT\\\" and u.id = ?";

            try (PreparedStatement command = conn.prepareStatement(employeeQuery)) {
                command.setInt(1, managerID);
                command.setInt(2, limit);
                command.setInt(3, offset);

                try (ResultSet result = command.executeQuery()) {
                    while (result.next()) {
                        try (PreparedStatement skill = conn.prepareStatement(technicalSkillQuery)) {
                            skill.setInt(1, result.getInt("u.id"));

                            try (ResultSet tsSkill = skill.executeQuery()) {
                                while (tsSkill.next()) {
                                    TechnicalSkill technicalSkill = new TechnicalSkill(tsSkill.getInt(1), tsSkill.getString(2), tsSkill.getInt(3));
                                    technicalSkillList.add(technicalSkill);
                                }
                            }
                            catch(SQLException e){
                                System.out.println(e);
                            }
                        }
                        try (PreparedStatement skill = conn.prepareStatement(productSkillQuery)) {
                            skill.setInt(1, result.getInt("u.id"));

                            try (ResultSet psSkill = skill.executeQuery()) {
                                while (psSkill.next()) {
                                    ProductSkill productSkill = new ProductSkill(psSkill.getInt(1), psSkill.getString(2), psSkill.getInt(3));
                                    productSkillList.add(productSkill);
                                }
                            }
                            catch(SQLException e){
                                System.out.println(e);
                            }
                        }
                        Employee employee = new Employee(result.getInt("u.id"),
                                result.getInt("u.manager_id"),
                                result.getString("name"),
                                technicalSkillList, productSkillList,
                                result.getString("u.image"));
                        employees.add(employee);
                        technicalSkillList = new ArrayList<TechnicalSkill>();
                        productSkillList = new ArrayList<ProductSkill>();

                    }
                }
            }

        }
        return employees;
    }

    @Override
    public List<Employee> searchEmployeesBySkillID(int skillID, int pageNumber, int limit) throws SQLException{
        List<Employee> employees = new ArrayList<>();
        List<TechnicalSkill> technicalSkillList = new ArrayList<TechnicalSkill>();
        List<ProductSkill> productSkillList = new ArrayList<ProductSkill>();

        if (pageNumber < 1) {
            pageNumber = 1;
        }
        int offset = (pageNumber - 1) * limit;
        try (Connection conn = db.getConnection()) {
            String employeeQuery = "select u.id, concat(u.first_name, \" \" , u.last_name) as name, u.manager_id " +
                    ", u.image from users u limit ? offset ?;";
            String technicalSkillQuery = " SELECT s.id, s.name,es.level FROM users u join employeeskill es on u.id = " +
                    "es.user_id join skills s on es.skill_id = s.id where type = \"TECHNICAL\" and u.id = ? and s.id=?; ";
            String productSkillQuery = "SELECT s.id, s.name,es.level FROM users u join employeeskill es on u.id = \" +\n" +
                    "\"es.user_id join skills s on es.skill_id = s.id where type = \\\"PRODUCT\\\" and u.id = ? and s.id=?;";

            try (PreparedStatement command = conn.prepareStatement(employeeQuery)) {
                command.setInt(1, limit);
                command.setInt(2, offset);

                try (ResultSet result = command.executeQuery()) {
                    while (result.next()) {
                        try (PreparedStatement skill = conn.prepareStatement(technicalSkillQuery)) {
                            skill.setInt(1, result.getInt("u.id"));
                            skill.setInt(2,skillID);

                            try (ResultSet tsSkill = skill.executeQuery()) {
                                while (tsSkill.next()) {
                                    TechnicalSkill technicalSkill = new TechnicalSkill(tsSkill.getInt(1), tsSkill.getString(2), tsSkill.getInt(3));
                                    technicalSkillList.add(technicalSkill);
                                }
                            }
                            catch(SQLException e){
                                System.out.println(e);
                            }
                        }
                        try (PreparedStatement skill = conn.prepareStatement(productSkillQuery)) {
                            skill.setInt(1, result.getInt("u.id"));
                            skill.setInt(2,skillID);

                            try (ResultSet psSkill = skill.executeQuery()) {
                                while (psSkill.next()) {
                                    ProductSkill productSkill = new ProductSkill(psSkill.getInt(1), psSkill.getString(2), psSkill.getInt(3));
                                    productSkillList.add(productSkill);
                                }
                            }
                            catch(SQLException e){
                                System.out.println(e);
                            }
                        }
                        Employee employee = new Employee(result.getInt("u.id"),
                                result.getInt("u.manager_id"),
                                result.getString("name"),
                                technicalSkillList, productSkillList,
                                result.getString("u.image"));
                        employees.add(employee);
                        technicalSkillList = new ArrayList<TechnicalSkill>();
                        productSkillList = new ArrayList<ProductSkill>();

                    }
                }
            }

        }
        return employees;
    }



    public List<Employee> getEmployeesByProjectID(int projectid) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        List<TechnicalSkill> technicalskillList = new ArrayList<TechnicalSkill>();
        List<ProductSkill> productskillList = new ArrayList<ProductSkill>();
        try (Connection conn = db.getConnection()) {
            String employeeQuery = "select u.id, concat(u.first_name, \" \" , u.last_name) as name, u.manager_id " +
                    ", u.image from users u join assignment a on u.id = a.employee_id where a.project_id = ?";
            String technicalSkillQuery = " SELECT s.id, s.name,es.level FROM users u join employeeskill es on u.id = " +
                    "es.user_id join skills s on es.skill_id = s.id where type = \"TECHNICAL\" and u.id = ? ";
            String productSkillQuery = "SELECT s.id, s.name,es.level FROM users u join employeeskill es on u.id = \" +\n" +
                    "\"es.user_id join skills s on es.skill_id = s.id where type = \\\"PRODUCT\\\" and u.id = ?";
            try (PreparedStatement command = conn.prepareStatement(employeeQuery)) {
                command.setInt(1, projectid);
                try (ResultSet result = command.executeQuery()) {
                    while (result.next()) {
                        try (PreparedStatement skill = conn.prepareStatement(technicalSkillQuery)) {
                            skill.setInt(1, result.getInt("u.id"));
                            try (ResultSet tsSkill = skill.executeQuery()) {
                                while (tsSkill.next()) {
                                    TechnicalSkill technicalSkill = new TechnicalSkill(tsSkill.getInt(1), tsSkill.getString(2), tsSkill.getInt(3));
                                    technicalskillList.add(technicalSkill);
                                }
                            } catch (SQLException e) {
                                System.out.println(e);
                            }
                        }
                        try (PreparedStatement skill = conn.prepareStatement(productSkillQuery)) {
                            skill.setInt(1, result.getInt("u.id"));
                            try (ResultSet psSkill = skill.executeQuery()) {
                                while (psSkill.next()) {
                                    ProductSkill productSkill = new ProductSkill(psSkill.getInt(1), psSkill.getString(2), psSkill.getInt(3));
                                    productskillList.add(productSkill);
                                }
                            } catch (SQLException e) {
                                System.out.println(e);
                            }
                        }
                        Employee employee = new Employee(result.getInt("u.id"),
                                result.getInt("u.manager_id"),
                                result.getString("name"),
                                technicalskillList, productskillList,
                                result.getString("u.image"));
                        employees.add(employee);
                        technicalskillList = new ArrayList<TechnicalSkill>();
                        productskillList = new ArrayList<ProductSkill>();
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
