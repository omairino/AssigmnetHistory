package com.assignments.proj.Api.model;

public class TechnicalSkill {
    private int id;
    private String name;
    private int level;

    public TechnicalSkill(int id, String name,int level) {
        this.id = id;
        this.name = name;
        this.level=level;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
