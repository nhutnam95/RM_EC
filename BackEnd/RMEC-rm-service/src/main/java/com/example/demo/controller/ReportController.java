package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.common.ApiResponse;
import com.example.demo.service.ReportService;

@RestController
public class ReportController {
	@Autowired
	private ReportService reportService;
	
	@GetMapping(value = "/ListBetwProject/{projectId}")
	public ApiResponse<Map<String,Object>> GetAllReportBetwProject(@PathVariable (name = "projectId") String projectId) {
		return reportService.GetAllReportBetwProject(projectId);
	}
}
