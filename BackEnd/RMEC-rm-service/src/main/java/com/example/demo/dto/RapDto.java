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
public class RapDto {

	private Long rapId;
	private Long projectId;
	private Long rfrId;
	private String accountName;
	private String From;
	private String To;
	private Float Fte;
	private String Status;
	private LocalDate rapCreateDate;
	private LocalDate rapUpdateDate;
	private boolean rapDelFlag;

}
