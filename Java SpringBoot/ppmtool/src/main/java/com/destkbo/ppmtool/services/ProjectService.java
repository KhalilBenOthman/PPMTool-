package com.destkbo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destkbo.ppmtool.domain.Project;
import com.destkbo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository ;
	
	public Project saveOrUpdateProject(Project project){
		
		//Logic
		
		return projectRepository.save(project);
	}
}