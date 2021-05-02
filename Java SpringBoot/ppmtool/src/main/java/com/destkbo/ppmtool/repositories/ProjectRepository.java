package com.destkbo.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.destkbo.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project ,Long>{
	
}