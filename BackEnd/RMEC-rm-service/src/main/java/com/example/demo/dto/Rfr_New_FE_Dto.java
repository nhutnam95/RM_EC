package com.example.demo.dto;

import org.json.simple.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rfr_New_FE_Dto {
	private Long rfrId;
	private String roleName;
	private String rankName;
	private String skillName;
	private String locationName;
	private JSONObject rfrEffortMonths;
}
