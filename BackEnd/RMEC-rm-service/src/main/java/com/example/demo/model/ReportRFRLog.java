package com.example.demo.model;

import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRFRLog {
	private List<HashMap<String, String>> report;
	private List<HashMap<String, String>> role;
	private List<HashMap<String, String>> rank;
	private List<HashMap<String, String>> skill;
	private List<HashMap<String, String>> location;


	
	
	

	
	
}
