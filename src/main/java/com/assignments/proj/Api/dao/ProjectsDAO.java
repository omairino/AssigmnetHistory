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
            String projectQuery = "SELECT p.id,p.name,p.start_date,p.description FROM project p";
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
                        Project pro2 = new Project(Rs.getInt(1), Rs.getString(2), Rs.getString(4), Rs.getDate(3), technicalSkillList, productSkillList);
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
                        Project pro2 = new Project(Rs.getInt(1), Rs.getString(2), Rs.getString(4), Rs.getDate(3), technicalSkillList, productSkillList);
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
//        try (Connection conn = db.getConnection()) {
//
//            String insertQueryProject = "INSERT INTO project (projectName,managerID, description,startDate)" +
//                    "VALUES (?,?,?,?)";
//            try (PreparedStatement fetch = conn.prepareStatement(insertQueryProject, Statement.RETURN_GENERATED_KEYS)) {
//                fetch.setString(1, item.getProjectName());
//                fetch.setString(2, String.valueOf(item.getManagerId()));
//                fetch.setString(3, item.getDescription());
//                fetch.setString(4, String.valueOf(item.getStartDate()));
//                fetch.executeUpdate();
//                try (ResultSet generatedID = fetch.getGeneratedKeys()) {
//                    if (generatedID.next())
//                        item.setId(generatedID.getInt(1));
//
//                    else
//                        throw new SQLException("Project insertion failed.");
//                }
//            }
//
//            StringBuilder insertProjectSkill = new StringBuilder("INSERT INTO projectsSkills (projectID, SkillID)\n" +
//                    "VALUES (?, ?)");
//            int sizeSkill = item.getSkills().size();
//            for (int i = 0; i < sizeSkill - 1; i++) {
//                insertProjectSkill.append(",VALUES (?, ?)");
//            }
//            insertProjectSkill.append(";");
//            try (PreparedStatement fetch = conn.prepareStatement(String.valueOf(insertProjectSkill), Statement.RETURN_GENERATED_KEYS)) {
//                for (int i = 0; i < sizeSkill - 1; i++) {
//                    fetch.setString(i + 1, String.valueOf(item.getId()));
//                    fetch.setString(i + 2, String.valueOf(item.getSkills().get(0)));
//                }
//                fetch.executeUpdate();
//            }
//        }

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

