package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.Skills;
import com.assignments.proj.Api.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class ProjectsDAO implements IProjectDAO {
    @Autowired
    private DBHandler db;

    @Override
    public List<Project> findAll() throws SQLException {
        List<Project> projectList = new ArrayList<Project>();
        List<Skills> technicalSkillList = new ArrayList<Skills>();
        List<Skills> productSkillList = new ArrayList<Skills>();

        try (Connection conn = db.getConnection()) {
            String projectQuery = "SELECT p.id,p.name,p.start_date,p.description,manager_id FROM project p";
            String technicalSkillQuery = "SELECT s.id,s.name FROM project p join projectskill ps on p.id = ps.project_id join skills s on ps.skill_id = s.id where type = \"TECHNICAL\" and p.id = ?";
            String productSkillQuery = "SELECT s.id,s.name FROM project p join projectskill ps on p.id = ps.project_id join skills s on ps.skill_id = s.id where type = \"PRODUCT\" and p.id = ?";


            try (PreparedStatement ps = conn.prepareStatement(projectQuery)) {


                try (ResultSet Rs = ps.executeQuery()) {

                    while (Rs.next()) {

                        //GET technical SKILL FOR EMPLOYEE
                        try (PreparedStatement skill = conn.prepareStatement(technicalSkillQuery)) {
                            skill.setInt(1, Rs.getInt("p.id"));

                            try (ResultSet tsskill = skill.executeQuery()) {
                                while (tsskill.next()) {
                                    Skills technicalSkill = new Skills(tsskill.getInt(1), tsskill.getString(2), 0);
                                    technicalSkillList.add(technicalSkill);
                                }
                            } catch (SQLException e) {
                                System.out.println(e);
                            }
                        }

                        //GET PRODUCT SKILL FOR EMPLOYEE
                        try (PreparedStatement skill = conn.prepareStatement(productSkillQuery)) {
                            skill.setInt(1, Rs.getInt("p.id"));

                            try (ResultSet psskill = skill.executeQuery()) {
                                while (psskill.next()) {
                                    Skills productSkill = new Skills(psskill.getInt(1), psskill.getString(2), 0);
                                    productSkillList.add(productSkill);
                                }
                            } catch (SQLException e) {
                                System.out.println(e);
                            }
                        }
                        Project pro2 = new Project(Rs.getInt(1), Rs.getString(2), Rs.getString(4), Rs.getDate(3), technicalSkillList, productSkillList,Rs.getInt(5));
                        projectList.add(pro2);
                        technicalSkillList = new ArrayList<Skills>();
                        productSkillList = new ArrayList<Skills>();
                    }
                }
            }
        }
        return projectList;
    }


    @Override
    public List<Project> getManagerProjects(int managerId) throws SQLException, ResultsNotFoundException {
        List<Project> projectList = new ArrayList<Project>();
        List<Skills> technicalSkillList = new ArrayList<Skills>();
        List<Skills> productSkillList = new ArrayList<Skills>();

        try (Connection conn = db.getConnection()) {
            String projectQuery = "SELECT p.id,p.name,p.start_date,p.description FROM project p where manager_id = ?";
            String technicalSkillQuery = "SELECT s.id,s.name FROM project p join projectskill ps on p.id = ps.project_id join skills s on ps.skill_id = s.id where type = \"TECHNICAL\" and p.id = ?";
            String productSkillQuery = "SELECT s.id,s.name FROM project p join projectskill ps on p.id = ps.project_id join skills s on ps.skill_id = s.id where type = \"PRODUCT\" and p.id = ?";

            try (PreparedStatement ps = conn.prepareStatement(projectQuery)) {

                ps.setInt(1, managerId);

                try (ResultSet Rs = ps.executeQuery()) {

                    while (Rs.next()) {

                        //GET technical SKILL FOR EMPLOYEE
                        try (PreparedStatement skill = conn.prepareStatement(technicalSkillQuery)) {
                            skill.setInt(1, Rs.getInt("p.id"));

                            try {
                                ResultSet tsskill = skill.executeQuery();
                                while (tsskill.next()) {
                                    Skills technicalSkill = new Skills(tsskill.getInt(1), tsskill.getString(2), 0);
                                    technicalSkillList.add(technicalSkill);
                                }
                            } catch (SQLException e) {
                                System.out.println(e);
                            }
                        }
                        //GET PRODUCT SKILL FOR EMPLOYEE
                        try (PreparedStatement skill = conn.prepareStatement(productSkillQuery)) {
                            ps.setInt(1, Rs.getInt("p.id"));

                            try {
                                ResultSet psskill = skill.executeQuery();
                                while (psskill.next()) {
                                    Skills productSkill = new Skills(psskill.getInt(1), psskill.getString(2), 0);
                                    productSkillList.add(productSkill);
                                }
                            } catch (SQLException e) {
                                System.out.println(e);
                            }
                        }
                        Project pro2 = new Project(Rs.getInt(1), Rs.getString(2), Rs.getString(4), Rs.getDate(3), technicalSkillList, productSkillList,managerId);
                        projectList.add(pro2);
                        technicalSkillList = new ArrayList<Skills>();
                        productSkillList = new ArrayList<Skills>();
                    }
                }
            }
        }


        return projectList;
    }

    @Override
    public Project add(Project item) throws SQLException {
        int projectID;
        try (Connection conn = db.getConnection()) {

            String insertQueryProject = "INSERT INTO project (name, manager_id, description,start_date)" +
                    "VALUES (?,?,?,?)";
            try (PreparedStatement fetch = conn.prepareStatement(insertQueryProject, Statement.RETURN_GENERATED_KEYS)) {
                fetch.setString(1, item.getName());
                fetch.setString(2, String.valueOf(item.getManagerID()));
                fetch.setString(3, item.getDescription());
                fetch.setString(4, String.valueOf(item.getStartDate()));
                fetch.executeUpdate();
                try (ResultSet generatedID = fetch.getGeneratedKeys()) {
                    if (generatedID.next())
                        projectID = generatedID.getInt(1);

                    else
                        throw new SQLException("Project insertion failed.");
                }
            }

            StringBuilder insertProjectSkill = new StringBuilder("INSERT INTO projectskill (project_id, skill_id,skill_level)\n" +
                    " VALUES (?, ?,?)");
            int sizeSkillProduct = item.getProductSkill().size();
            int sizeSkillTechnical = item.getTechnicalSkill().size();
            for (int i = 0; i < (sizeSkillProduct+sizeSkillTechnical) - 1; i++) {
                insertProjectSkill.append(", (?, ?, ?)");
            }

            try (PreparedStatement fetch = conn.prepareStatement(String.valueOf(insertProjectSkill), Statement.RETURN_GENERATED_KEYS)) {
                int counter = 0;
                for (int i = 1; i <= (sizeSkillProduct)*3 ; i += 3) {
                    fetch.setString(i, String.valueOf(projectID));
                    fetch.setString(i + 1, String.valueOf(item.getProductSkill().get(counter).getId()));
                    fetch.setString(i + 2, String.valueOf(item.getProductSkill().get(counter).getLevel()));
                    ++counter;
                }
                for (int i = 1; i <= (sizeSkillTechnical)*3 ; i += 3) {
                    fetch.setString(i, String.valueOf(projectID));
                    fetch.setString(i + 1, String.valueOf(item.getTechnicalSkill().get(counter).getId()));
                    fetch.setString(i + 2, String.valueOf(item.getTechnicalSkill().get(counter).getLevel()));
                    ++counter;
                }
                fetch.executeUpdate();
            }
        }

        return item;
    }


    @Override
    public Project update(Project item) throws SQLException {
        return null;
    }

    @Override
    public Project delete(Project item) throws SQLException {
        return null;
    }

    @Override
    public Project find(int id) throws SQLException {
        return null;
    }

}

