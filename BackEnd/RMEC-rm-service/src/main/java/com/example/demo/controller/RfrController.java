package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.common.ApiResponse;
import com.example.demo.error.IdProjectWrongFormatException;
import com.example.demo.error.IdRequestFailsException;
import com.example.demo.error.ProjectNotFound;
import com.example.demo.error.RFRNotFoundException;
import com.example.demo.error.RequestWrongFormat;
import com.example.demo.service.RfrService;

@RestController
public class RfrController {
	@Autowired
	private RfrService rfrService;

	@GetMapping(value = "/listRFR/{projectId}")
	public ApiResponse<Map<Object,Object>> findAllRfr(@PathVariable (name = "projectId") String projectId) {
		return rfrService.findAllRfr_ApiMapType(projectId);
	}
	
//	@PostMapping("/SubmitRFRQuickAdd")
//	 public ApiResponse<String> submitRFRQuickAdd(@RequestBody RequestSubmitRFRQuickAdd requestSubmitRFRQuickAdd ){
//		try {
//			rfrService.submitRFRQuickAdd(requestSubmitRFRQuickAdd);
//		} catch (DateRFROutBoundsProjectException e) {
//			// TODO Auto-generated catch block
//			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
//		} catch (RFRRoleIdNotFound e) {
//			// TODO Auto-generated catch block
//			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
//		}
//		catch (RFRSkillIdNotFound e) {
//			// TODO Auto-generated catch block
//			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
//		}
//		catch (RFRRankIdNotFound e) {
//			// TODO Auto-generated catch block
//			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
//		}
//		catch (RFRLocationIdNotFound e) {
//			// TODO Auto-generated catch block
//			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
//		}
//		return new ApiResponse<>(HttpStatus.OK,"...OK", new Throwable());
//	}
	
	@PostMapping("/saveRFR")
	public ApiResponse<String> saveRfr(@RequestBody Map<String, Object> rfrByProjectId){
		try {
			rfrService.saveRFRbyProjectId(rfrByProjectId);
			return  new ApiResponse<>(HttpStatus.OK,"...OK", new Throwable());
		} catch (IdRequestFailsException e) {
			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
		}
		catch (ProjectNotFound e) { 
			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
		}
		catch (RFRNotFoundException e) { 
			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
		}
		catch (IdProjectWrongFormatException e) { 
			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
		}
		catch (RequestWrongFormat e) { 
			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,"...", new Throwable(e));
		}
	}
}
