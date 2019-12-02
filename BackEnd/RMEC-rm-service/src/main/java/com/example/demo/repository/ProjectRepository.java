package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Projects;


@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
	@Query("SELECT e FROM Projects e WHERE e.projectDelFlag = false")
	List<Projects> findAll();
	@Query("SELECT e FROM Projects e WHERE e.projectId = :projectId AND e.projectDelFlag = false")
	Projects findProjectByProjectId(@Param("projectId") Long projectId);
	@Query("SELECT e.projectDelFlag FROM Projects e WHERE e.projectId = :projectId")
	boolean checkDelFlagProjectById(@Param("projectId") Long projectId); 
}
