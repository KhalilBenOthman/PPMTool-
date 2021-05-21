package com.destkbo.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destkbo.ppmtool.domain.Backlog;
import com.destkbo.ppmtool.domain.Project;
import com.destkbo.ppmtool.domain.ProjectTask;
import com.destkbo.ppmtool.exceptions.ProjectNotFoundException;
import com.destkbo.ppmtool.repositories.BacklogRepository;
import com.destkbo.ppmtool.repositories.ProjectRepository;
import com.destkbo.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository ;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier , ProjectTask projectTask) {
		
		try {
			
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
			
			
			projectTask.setBacklog(backlog);
			
			Integer BacklogSequence = backlog.getPTSequence();
			BacklogSequence++;
			backlog.setPTSequence(BacklogSequence);
			
			projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);
			
			if(projectTask.getPriority() == null || projectTask.getPriority()==0) {
				projectTask.setPriority(3);
			}
			
			if(projectTask.getStatus()==null || projectTask.getStatus()=="" ) {
				projectTask.setStatus("To_Do");;
			}
			return projectTaskRepository.save(projectTask);
			
			
			
		} catch (Exception e) {
			throw new ProjectNotFoundException ("Project not Found");
		}
		
		
		
		
	}

	public Iterable<ProjectTask> findBacklogById(String backlog_id) {
		Project project = projectRepository.findByProjectIdentifier(backlog_id);
		
		if(project==null) {
			throw new ProjectNotFoundException("Project with ID : '"+backlog_id+"'does not exist");
		}
		
		
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
	}
	
	public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id) {
		
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog == null) {
			throw new ProjectNotFoundException("Project with ID : '"+backlog_id+"'does not exist");
		}
		
		
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask == null) {
			throw new ProjectNotFoundException("Project task with ID : '"+pt_id+"'not found");
		}
		
		if(!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project Task'"+pt_id+"'does not exist in project : '"+backlog_id);
		}
		
		
		
		return projectTask;
	}
	
	
	public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id,String pt_id) {
		
		ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
		
		projectTask = updatedTask;
		
		return projectTaskRepository.save(projectTask);
		
	}
	
	public void deletePTByProjectSequence(String backlog_id,String pt_id) {
		
		ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
		
		/*Backlog backlog = projectTask.getBacklog();
		List<ProjectTask> pts = backlog.getProjectTasks();
		pts.remove(projectTask);
		backlogRepository.save(backlog);*/
		
		projectTaskRepository.delete(projectTask);
	}
	
	

}
