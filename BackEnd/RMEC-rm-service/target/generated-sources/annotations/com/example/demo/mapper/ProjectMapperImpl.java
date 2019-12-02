package com.example.demo.mapper;

import com.example.demo.dto.ProjectsDto;
import com.example.demo.dto.Projects_FE_Dto;
import com.example.demo.model.Projects;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectsDto projectToProjectDto(Projects project) {
        if ( project == null ) {
            return null;
        }

        ProjectsDto projectsDto = new ProjectsDto();

        projectsDto.setProjectId( project.getProjectId() );
        projectsDto.setProjectName( project.getProjectName() );
        projectsDto.setProjectFrom( project.getProjectFrom() );
        projectsDto.setProjectTo( project.getProjectTo() );
        projectsDto.setProjectCreateDate( project.getProjectCreateDate() );
        projectsDto.setProjectUpdateDate( project.getProjectUpdateDate() );
        projectsDto.setProjectDelFlag( project.isProjectDelFlag() );

        return projectsDto;
    }

    @Override
    public Projects_FE_Dto projectToProjects_FE_Dto(Projects project) {
        if ( project == null ) {
            return null;
        }

        Projects_FE_Dto projects_FE_Dto = new Projects_FE_Dto();

        projects_FE_Dto.setProjectId( project.getProjectId() );
        projects_FE_Dto.setProjectName( project.getProjectName() );
        projects_FE_Dto.setProjectFrom( project.getProjectFrom() );
        projects_FE_Dto.setProjectTo( project.getProjectTo() );

        return projects_FE_Dto;
    }

    @Override
    public Projects projectDtoToProject(ProjectsDto projectDto) {
        if ( projectDto == null ) {
            return null;
        }

        Projects projects = new Projects();

        projects.setProjectId( projectDto.getProjectId() );
        projects.setProjectName( projectDto.getProjectName() );
        projects.setProjectFrom( projectDto.getProjectFrom() );
        projects.setProjectTo( projectDto.getProjectTo() );
        projects.setProjectCreateDate( projectDto.getProjectCreateDate() );
        projects.setProjectUpdateDate( projectDto.getProjectUpdateDate() );
        projects.setProjectDelFlag( projectDto.isProjectDelFlag() );

        return projects;
    }
}
