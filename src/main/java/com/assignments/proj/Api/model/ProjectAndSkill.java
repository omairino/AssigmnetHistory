package com.assignments.proj.Api.model;

import java.sql.Date;
import java.util.List;

public class ProjectAndSkill {
    private int projectID;
    private int skillID;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public ProjectAndSkill(int projectID, int skillID) {
        this.projectID = projectID;
        this.skillID = skillID;
    }
}
