package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.common.ApiResponse;
import com.example.demo.service.RatService;

@RestController
public class RatController {

	@Autowired
	private RatService ratService;
	
	@GetMapping("/listRAT/{projectId}")
	public ApiResponse<Map<Object,Object>> findAllRat(@PathVariable (name = "projectId") Long projectId){
		return ratService.findAllRat_ApiMapType(projectId);
	}
}
