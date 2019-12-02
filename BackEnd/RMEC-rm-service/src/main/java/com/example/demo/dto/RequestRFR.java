package com.example.demo.dto;
import java.time.LocalDate;
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
public class RequestRFR {
	private Long id;
	private Long role_id;
	private Long rank_id;
	private Long skill_id;
	private Long location_id;
	private List<LocalDate> from_to;
	private int quantity;

}


