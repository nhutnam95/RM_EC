package com.example.demo.dto;

import java.util.List;
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
public class ReportBetwprojectDto {
	private Long rfrId;
	private String role;
	private String rank;
	private String skill;
	private String location;
	private List<String> M1MnSum;
}
