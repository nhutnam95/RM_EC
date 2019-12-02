package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.ProjectsDto;
import com.example.demo.dto.Projects_FE_Dto;
import com.example.demo.model.Projects;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
	ProjectsDto projectToProjectDto(Projects project);
	Projects_FE_Dto projectToProjects_FE_Dto(Projects project);
	Projects projectDtoToProject(ProjectsDto projectDto);
}
