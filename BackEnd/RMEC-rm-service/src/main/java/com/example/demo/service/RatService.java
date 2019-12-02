package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.Rap_FE_Dto;
import com.example.demo.mapper.RapMapper;
import com.example.demo.model.Rap;

@Service
public class RatService {
	@Autowired
	private RapService rapService;
	@Autowired
	private RapMapper rapMapper;
	
	public ApiResponse<Map<Object,Object>> findAllRat_ApiMapType(Long projectId){
		// Retrieve list of RAT
		List<Rap> listRat = rapService.findAllRapByProjectId_AvailableStatus(projectId);
		// Check null of Rat's list
		if (listRat.isEmpty()) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND);
		}
		// convert list of Rat model to DTO
		List<Rap_FE_Dto> listRap_FE_Dto = listRat.stream().map(rapMapper::rapToRap_FE_Dto)
				.collect(Collectors.toList());
		Map<Object,Object> listMapAPI = new HashMap<Object, Object>(); 
		listMapAPI.put("project_id", projectId);
		listMapAPI.put("rat", listRap_FE_Dto);
		
		return new ApiResponse<>(listMapAPI, HttpStatus.OK);
	}
}
