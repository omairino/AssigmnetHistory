package com.assignments.proj.Api.Model;


import java.util.Objects;

public class Project {

    private int id;
    private String name;
    private String description;
 

    public Project(int id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
       
    }
    
	public static void copy(Project temp, Project item) {

		temp.setId(item.id);
		temp.setName(item.name);
		temp.setDescription(item.description);
	
	}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
