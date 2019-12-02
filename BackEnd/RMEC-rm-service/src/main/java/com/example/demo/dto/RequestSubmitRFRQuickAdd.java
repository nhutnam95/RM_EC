package com.example.demo.dto;
import java.util.LinkedList;

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
public class RequestSubmitRFRQuickAdd {
	long project_id;
	String token;
	LinkedList<RequestRFR> rfr;
	
}
