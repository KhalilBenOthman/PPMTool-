package com.destkbo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destkbo.ppmtool.domain.Project;
import com.destkbo.ppmtool.exceptions.ProjectIdException;
import com.destkbo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository ;
	
	public Project saveOrUpdateProject(Project project){
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception e){
			throw new ProjectIdException("Project ID'"+project.getProjectIdentifier().toUpperCase()+"'Already Existe.");
			
		}
		
		
	}
	
	public Project findProjectByIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Project does not Existe.");
		}
		
		
		
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	
	
	
	
	
	
}
