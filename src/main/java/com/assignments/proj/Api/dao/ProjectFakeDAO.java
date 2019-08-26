package com.assignments.proj.Api.DAO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.assignments.proj.Api.Model.Assignment;
import com.assignments.proj.Api.Model.AssignmentHistory;
import com.assignments.proj.Api.Model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectFakeDAO implements ProjectCollection<Project>{
	private List<Project> project = Arrays.asList(
			new Project(1,"Vodaphone","Java,Html,Css"),
			new Project(1,"Hotmobile","Python,Css"),
			new Project(1,"Cellcom","C++,Css"),
			new Project(1,"Bezeq","Java,Css")
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
			project.copy(temp, item);
		
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
		// TODO Auto-generated method stub
		return null;
	}




}
