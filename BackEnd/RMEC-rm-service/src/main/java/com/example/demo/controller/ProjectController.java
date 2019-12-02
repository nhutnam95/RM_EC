package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.common.ApiResponse;
import com.example.demo.service.ProjectService;

@RestController
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@GetMapping(value = "/listProject")
	public ApiResponse<Map<Object,Object>> findAllProject() {
		return projectService.findAllProject_ApiMapType();
	}

//	@ApiOperation(value = "API Report betw-Project")
//	@GetMapping(value = "/project/reportBetwProject/{projectId}")
//	public ApiResponse<List<ReportBetwprojectLogDto>> reportBetwProject(@PathVariable(name = "projectId") Long projectId) {
//		List<ReportbetwprojectLog> listReport = reportbetwprojectLogService.findByProjectId(projectId);
//		// if list report is empty, return NOT_FOUND status
//		if (listReport.isEmpty()) {
//			return new ApiResponse<>(HttpStatus.NOT_FOUND);
//		}
//		//else map list report to list reportDto
//		List<ReportBetwprojectLogDto> listReportDto = listReport.stream().map(reportbetwMapper::reportbetwDto)
//				.collect(Collectors.toList());
//		return new ApiResponse<>(listReportDto, HttpStatus.OK);
//	}
}
