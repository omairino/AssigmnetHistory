package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.BadRequestException;
import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.ListSkills;
import com.assignments.proj.Api.model.Skills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillDAO implements IDAO<ListSkills>{

    @Autowired
    private DBHandler db;


    @Override
    public List<ListSkills> findAll() throws SQLException, BadRequestException {
        List<ListSkills> skillsList= new ArrayList<ListSkills>();
        List<Skills> technicalSkillList = new ArrayList<Skills>();
        List<Skills> productSkillList = new ArrayList<Skills>();

        try (Connection conn = db.getConnection()) {
            String technicalSkillQuery = "SELECT id,name FROM skills where type=\"TECHNICAL\"" ;
            String productSkillQuery = "SELECT id,name FROM skills where type=\"PRODUCT\"" ;

             try(PreparedStatement ps = conn.prepareStatement(technicalSkillQuery)){

                 try (ResultSet tsskill = ps.executeQuery()) {
                     while (tsskill.next()) {
                         Skills technicalSkill = new Skills(tsskill.getInt(1), tsskill.getString(2), 0);
                         technicalSkillList.add(technicalSkill);
                     }
             }

        }

            try(PreparedStatement ps = conn.prepareStatement(productSkillQuery)){

                try (ResultSet psskill = ps.executeQuery()) {
                    while (psskill.next()) {
                        Skills productSkill = new Skills(psskill.getInt(1), psskill.getString(2), 0);
                        productSkillList.add(productSkill);
                    }
                }

            }

            skillsList.add(new ListSkills(technicalSkillList,productSkillList));
        if (skillsList.isEmpty())
            throw new ResultsNotFoundException("Couldn't find Skills List");

        return skillsList;
    }}


    @Override
    public ListSkills add(ListSkills item) throws SQLException {
        return null;
    }

    @Override
    public ListSkills update(ListSkills item) throws SQLException {
        return null;
    }

    @Override
    public ListSkills delete(ListSkills item) throws SQLException {
        return null;
    }

    @Override
    public ListSkills find(int id) throws SQLException {
        return null;
    }
}
