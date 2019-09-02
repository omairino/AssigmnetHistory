package com.assignments.proj.Api.dao;

import com.assignments.proj.Api.exceptions.ResultsNotFoundException;
import com.assignments.proj.Api.model.Project;

import java.sql.SQLException;
import java.util.List;

public interface IProjectDAO extends IDAO<Project>{

    List<Project> getManagerProjects(int managerId) throws SQLException, ResultsNotFoundException;
}
