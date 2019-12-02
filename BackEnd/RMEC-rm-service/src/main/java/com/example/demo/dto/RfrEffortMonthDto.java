package com.example.demo.dto;

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
public class RfrEffortMonthDto {

	private Long rfrEffortmonthId;
	private Long rfrId;
	private String effortName;
	private float effort;
	
}
