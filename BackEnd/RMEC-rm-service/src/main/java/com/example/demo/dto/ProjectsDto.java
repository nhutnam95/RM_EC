package com.example.demo.dto;

import java.time.LocalDate;
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
public class ProjectsDto {
	private Long projectId;
	private String projectName;
	private LocalDate projectFrom;
	private LocalDate projectTo;
	private LocalDate projectCreateDate;
	private LocalDate projectUpdateDate;
	private boolean projectDelFlag;
}
