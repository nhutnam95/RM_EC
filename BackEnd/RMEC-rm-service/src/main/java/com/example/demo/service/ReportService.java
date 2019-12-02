package com.example.demo.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.common.ApiResponse;
import com.example.demo.dto.ReportBetwprojectDto;
import com.example.demo.model.Projects;
import com.example.demo.model.Rap;
import com.example.demo.model.Rfr_New;
import com.example.demo.repository.ProjectRepository;

@Service
public class ReportService {
	@Autowired
	private RfrService rfrService;
	@Autowired
	private RapService rapService;
	@Autowired
	private ProjectRepository projectRepository;

	public ApiResponse<Map<String,Object>> GetAllReportBetwProject(String projectId_StringType){
		long projectId;
		try {
			projectId= Long.parseLong(projectId_StringType);
		}
		catch (NullPointerException e) {
			return new ApiResponse<>(HttpStatus.BAD_REQUEST);
		}
		catch (NumberFormatException e) {
			return new ApiResponse<>(HttpStatus.BAD_REQUEST);
		}
		if(projectId<=0) {
			return new ApiResponse<>(HttpStatus.BAD_REQUEST);
		}
		//Cac list ko duoc null
		//Rat phai du so thang
		List<Rfr_New> listRfr =rfrService.findAllRfr_NewByProjectId(projectId);
		List<Rap> listRat = rapService.findAllRapByProjectId_AvailableStatus(projectId);
		Map<String, Float> mapRATtoMonth=convertListRatToMapFTE(listRat);
		//Caculate months of Project
		Projects project= projectRepository.findById(projectId).get();
		LocalDate pF = project.getProjectFrom();
		LocalDate pT = project.getProjectTo();
		ArrayList<Long> listIdRfr = new ArrayList<Long>();
		long monthofProject = ChronoUnit.MONTHS.between(pF.withDayOfMonth(1), pT.withDayOfMonth(1)) + 1;
		List<ReportBetwprojectDto> listReport= new ArrayList<ReportBetwprojectDto>();
		for (int i = 0; i < listRfr.size(); i++) {
			Rfr_New rfr= listRfr.get(i);
			List<String> M1MnSum=new ArrayList<String>();
			// for loop for creating M1MnSum
			for (int j = 1; j <= monthofProject; j++) {
				String key=rfr.getRfrId().toString()+"M"+j;
				if (mapRATtoMonth.get(key)!=null) {
					M1MnSum.add(mapRATtoMonth.get(key).toString()+"/"+rfr.getRfrEffortMonths().get("m"+j));
					continue;
				}
				M1MnSum.add("0/"+rfr.getRfrEffortMonths().get("m"+j));
			}
			listReport.add(new ReportBetwprojectDto(rfr.getRfrId(), rfr.getRoleName(), rfr.getRankName(), rfr.getSkillName(), rfr.getSkillName(), M1MnSum));
		}
		Map<String,Object> listMapAPI = new HashMap<String, Object>();
		listMapAPI.put("project_id", projectId);
		listMapAPI.put("report", listReport);
		return new ApiResponse<>(listMapAPI, HttpStatus.OK);
	}
	private Map<String, Float> convertListRatToMapFTE(List<Rap> listRat){
		Map<String, Float> mapRATtoMonth= new HashMap<String, Float>();
		for (int i = 0; i < listRat.size(); i++) {
			Rap rat = listRat.get(i);
			float fte=rat.getFte();
			int from= Integer.parseInt(rat.getFrom().substring(1));
			int to= Integer.parseInt(rat.getTo().substring(1));
			for (int j = from; j <= to; j++) {
				String key= rat.getRfrId().toString()+"M"+j;
				if(mapRATtoMonth.get(key)!=null) {
					mapRATtoMonth.put(key, mapRATtoMonth.get(key)+fte);
					continue;
				}
				mapRATtoMonth.put(key, fte);
			}

		}
		return mapRATtoMonth;
	}
}
