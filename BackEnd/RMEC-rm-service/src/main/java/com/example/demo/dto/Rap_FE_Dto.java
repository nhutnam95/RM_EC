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
public class Rap_FE_Dto {

	private Long rfrId;
	private String accountName;
	private String From;
	private String To;
	private float Fte;
	private String Status;
}
