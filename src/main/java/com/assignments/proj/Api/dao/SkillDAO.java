package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.Assignment;
import com.assignments.proj.Api.model.ProductSkill;
import com.assignments.proj.Api.model.Skill;
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
public class SkillDAO implements IDAO<Skill>{

    @Autowired
    private DBHandler db;


    @Override
    public List<Skill> findAll() throws SQLException {
        List<Skill> skillsList= new ArrayList<Skill>();
        List<TechnicalSkill> technicalSkillList = new ArrayList<TechnicalSkill>();
        List<ProductSkill> productSkillList = new ArrayList<ProductSkill>();

        try (Connection conn = db.getConnection()) {
            String technicalSkillQuery = "SELECT id,name FROM skills where type=\"TECHNICAL\"" ;
            String productSkillQuery = "SELECT id,name FROM skills where type=\"PRODUCT\"" ;

             try(PreparedStatement ps = conn.prepareStatement(technicalSkillQuery)){

                 try (ResultSet tsskill = ps.executeQuery()) {
                     while (tsskill.next()) {
                         TechnicalSkill technicalSkill = new TechnicalSkill(tsskill.getInt(1), tsskill.getString(2), 0);
                         technicalSkillList.add(technicalSkill);
                     }
             }

        }

            try(PreparedStatement ps = conn.prepareStatement(productSkillQuery)){

                try (ResultSet psskill = ps.executeQuery()) {
                    while (psskill.next()) {
                        TechnicalSkill technicalSkill = new TechnicalSkill(psskill.getInt(1), psskill.getString(2), 0);
                        technicalSkillList.add(technicalSkill);
                    }
                }

            }

            skillsList.add(new Skill(technicalSkillList,productSkillList));
        if (skillsList.isEmpty())
            throw new ResultsNotFoundException("Couldn't find Skills List");

        return null;
    }}


    @Override
    public Skill add(Skill item) throws SQLException {
        return null;
    }

    @Override
    public Skill update(Skill item) throws SQLException {
        return null;
    }

    @Override
    public Skill delete(Skill item) throws SQLException {
        return null;
    }

    @Override
    public Skill find(int id) throws SQLException {
        return null;
    }
}
