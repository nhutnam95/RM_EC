package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.common.ApiResponse;
import com.example.demo.dto.RequestRap;
import com.example.demo.error.FteWrongFormatException;
import com.example.demo.error.IdRfrWrongFormatExceptions;
import com.example.demo.error.ProjectNotFound;
import com.example.demo.error.RFRNotFoundException;
import com.example.demo.error.RequestWrongFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.demo.dto.Rap_FE_Dto;
import com.example.demo.mapper.RapMapper;
import com.example.demo.model.Rap;
import com.example.demo.service.RapService;

@RestController
public class RapController {

	@Autowired
	RapService rapService;
	@Autowired
	private RapMapper rapMapper;

	@PostMapping("/saveRAP")
	public ApiResponse<String> saveRAO(@RequestBody RequestRap requestRap){
		System.out.println(requestRap);
		try {
			rapService.saveRAP(requestRap);
		} catch (ProjectNotFound | RFRNotFoundException | IdRfrWrongFormatExceptions | RequestWrongFormat | FteWrongFormatException e) {
			// TODO Auto-generated catch block
			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
		}
		return new ApiResponse<>(HttpStatus.OK);
	}

	@GetMapping("/listRAP/{projectId}")
	public ApiResponse<Map<Object,Object>> findAllRap(@PathVariable (name = "projectId") Long projectId){
		// Retrieve list of RAP
		List<Rap> listRap = rapService.findAllRapByProjectId(projectId);
		// Check null of Rap's list
		if (listRap.isEmpty()) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND);
		}
		// convert list of Rap model to DTO
		List<Rap_FE_Dto> listRap_FE_Dto = listRap.stream().map(rapMapper::rapToRap_FE_Dto)
				.collect(Collectors.toList());
		Map<Object,Object> listMapAPI = new HashMap<Object, Object>(); 
		listMapAPI.put("project_id", projectId);
		listMapAPI.put("rap", listRap_FE_Dto);
		
		return new ApiResponse<>(listMapAPI, HttpStatus.OK);
	}
}
