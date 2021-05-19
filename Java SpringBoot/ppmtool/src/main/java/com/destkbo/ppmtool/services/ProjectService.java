package com.destkbo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destkbo.ppmtool.domain.Backlog;
import com.destkbo.ppmtool.domain.Project;
import com.destkbo.ppmtool.exceptions.ProjectIdException;
import com.destkbo.ppmtool.repositories.BacklogRepository;
import com.destkbo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository ;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	public Project saveOrUpdateProject(Project project){
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			if(project.getId()==null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			
			if(project.getId()!=null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			
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
	
	public void deleteProjectByIdentifier(String projectId){
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project ==null) {
			throw new ProjectIdException("Cannot , Project with'"+projectId+"'This project does not existe");
		}
		projectRepository.delete(project);
	}
	
	
	
	
	
}
