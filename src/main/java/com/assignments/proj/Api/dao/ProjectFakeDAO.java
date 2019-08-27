package com.assignments.proj.Api.dao;

import java.util.Arrays;

import java.util.List;

import com.assignments.proj.Api.model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectFakeDAO implements ProjectCollection<Project>{
	private List<Project> project = Arrays.asList(
			new Project(1,"Vodaphone","Java,Html,Css"),
			new Project(2,"Hotmobile","Python,Css"),
			new Project(3,"Cellcom","C++,Css"),
			new Project(4,"Bezeq","Java,Css")
			);

	@Override
	public List<Project> getAllItems() {
		// TODO Auto-generated method stub
		return project;
	}

	@Override
	public Project insert(Project item) {
		if (!project.contains(item)){
			item.setId(project.size());
			project.add(item);
			return item;
		}
		return null;
	}

	@Override
	public Project update(Project item) {
		Project temp;

		int index = project.indexOf(item);

		if (index >= 0){
			temp = project.get(index);
			Project.copy(temp, item);
		
			return temp;
		}
		return null;
	}

	@Override
	public Project delete(Project item) {
		if (project.remove(item))
			return item;
		return null;
	}

	@Override
	public Project find(int id) {
		for(Project p : project) {
			if(p.getId() == id) {
				return p;
			}
		}
		
		// TODO Auto-generated method stub
		return project.get(1);
	}




}
