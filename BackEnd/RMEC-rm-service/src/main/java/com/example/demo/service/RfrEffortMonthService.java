package com.example.demo.service;

import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.RfrEffortMonth;
import com.example.demo.repository.RfrEffortMonthRepository;
@Service
public class RfrEffortMonthService {
	@Autowired
	RfrEffortMonthRepository rfrEffortMonthRepository;
	
	public List<RfrEffortMonth> findAllRfrEffortMonthByRfrId(Long rfrId) {
		List<RfrEffortMonth> listRfrEffortMonth = rfrEffortMonthRepository.findAllRfrEffortMonthByRfrId(rfrId);
		return listRfrEffortMonth;
	}
	public JSONObject findAllRfrEffortMonthByRfrId_JsonObjectType(Long rfrId){
		//Retrieve RfrEffortMonth list from database
		List<RfrEffortMonth> listRfrEffortMonth = rfrEffortMonthRepository.findAllRfrEffortMonthByRfrId(rfrId);
		//Use JsonObject to response RfrEffortMonth property
		JSONObject list_JsonObject = new JSONObject();
		for (int i = 0; i < listRfrEffortMonth.size(); i++) {
			list_JsonObject.put(listRfrEffortMonth.get(i).getEffortName(),listRfrEffortMonth.get(i).getEffort());
		}
		return list_JsonObject;
	}
}
