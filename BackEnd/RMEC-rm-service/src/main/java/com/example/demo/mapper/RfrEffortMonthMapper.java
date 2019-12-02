package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.RfrEffortMonthDto;
import com.example.demo.dto.RfrEffortMonth_FE_Dto;
import com.example.demo.model.RfrEffortMonth;

@Mapper(componentModel = "spring")
public interface RfrEffortMonthMapper {

	RfrEffortMonthDto rfrEffortMonthToRfrEffortMonthDto(RfrEffortMonth rfrEffortMonth);
	RfrEffortMonth_FE_Dto rfrEffortMonthToRfrEffortMonth_FE_Dto(RfrEffortMonth rfrEffortMonth);
	RfrEffortMonth rfrEffortMonthDtoToRfrEffortMonth(RfrEffortMonthDto rfrEffortMonthDto);	
}
