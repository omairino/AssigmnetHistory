package com.assignments.proj.Api.model;

import com.assignments.proj.Api.exceptions.LevelValidityException;

public class projectAndSkill {
    static private int MAXLEVEL = 5;
    private int id;
    private int level;

    public projectAndSkill(int id, int level) throws LevelValidityException {
        this.id = id;
        if (level <= MAXLEVEL && level > 0) {
            this.level = level;
        } else
            throw new LevelValidityException("level must be maximum 5 and minimum 0");

    }

    public static int getMAXLEVEL() {
        return MAXLEVEL;
    }

    public static void setMAXLEVEL(int MAXLEVEL) {
        projectAndSkill.MAXLEVEL = MAXLEVEL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
