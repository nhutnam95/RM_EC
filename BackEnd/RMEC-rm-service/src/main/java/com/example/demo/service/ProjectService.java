package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.common.ApiResponse;
import com.example.demo.dto.Projects_FE_Dto;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.model.Projects;
import com.example.demo.repository.ProjectRepository;

@Service
public class ProjectService{
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ProjectMapper projectMapper;

	public List<Projects> findAll() {
		List<Projects> listProject = projectRepository.findAll();
		return listProject;
	}

	public Projects findProjectById(Long projectId) {
		Projects pro = projectRepository.findProjectByProjectId(projectId);
		return pro;
	}
	public boolean checkProjectExist(Long id){
		if ((projectRepository.existsById(id))&&(!projectRepository.checkDelFlagProjectById(id))) {
			return true;
		}
		return false;
	}
	public ApiResponse<Map<Object,Object>> findAllProject_ApiMapType(){
		List<Projects> listProject = findAll();
		// if list project is empty, return NOT_FOUND status
		if (listProject.isEmpty()) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND);
		}
		// else convert project model list to projectDto
		List<Projects_FE_Dto> listProject_FE_Dto = listProject.stream().map(projectMapper::projectToProjects_FE_Dto)
				.collect(Collectors.toList());
		// Use a HashMap variable to store data and response to front-end side
		Map<Object,Object> listMapAPI=new HashMap(); 
		listMapAPI.put("projects", listProject_FE_Dto);
		return new ApiResponse<>(listMapAPI, HttpStatus.OK);
		
	}
}
