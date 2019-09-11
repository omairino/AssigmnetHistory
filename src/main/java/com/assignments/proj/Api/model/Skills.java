package com.assignments.proj.Api.model;

public class Skills {
    private int id;
    private String name;
    private int level;

    public Skills(int id, String name , int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
