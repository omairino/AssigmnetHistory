package com.assignments.proj.Api.model;

import java.util.List;

public class Skill {

    private List<TechnicalSkill> technicalSkills;
    private List<ProductSkill> productSkills;

    public Skill(List<TechnicalSkill> tecknicalSkills, List<ProductSkill> productSkills) {
        this.technicalSkills = tecknicalSkills;
        this.productSkills = productSkills;
    }

    public List<TechnicalSkill> getTecknicalSkills() {
        return technicalSkills;
    }

    public List<ProductSkill> getProductSkills() {
        return productSkills;
    }

    public void setTecknicalSkills(List<TechnicalSkill> tecknicalSkills) {
        this.technicalSkills = tecknicalSkills;
    }

    public void setProductSkills(List<ProductSkill> productSkills) {
        this.productSkills = productSkills;
    }
}
