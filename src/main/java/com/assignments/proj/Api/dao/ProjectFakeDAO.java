package com.assignments.proj.Api.DAO;

import java.util.List;

import com.assignments.proj.Api.Model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectFakeDAO implements IDAO<Project>{
	private List<Project> project;

	public ProjectFakeDAO(List<Project> project) {
		super();
		this.project = project;
	}

	public List<Project> getAllItems() {
		return project;
	}

	public Project insert(Project item) {
		return null;
	}

	public Project update(Project item) {
		return null;
	}

	public Project delete(Project item) {
		return null;
	}

	public int numberOfPages(int id, int limit) {
		return 0;
	}






}
