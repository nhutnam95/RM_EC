package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.RapDto;
import com.example.demo.dto.Rap_FE_Dto;
import com.example.demo.model.Rap;

@Mapper(componentModel = "spring")
public interface RapMapper {
	
	RapDto rapToRapDto(Rap rap);
	Rap_FE_Dto rapToRap_FE_Dto(Rap rap);
	Rap rapDtoToRap(RapDto rapDto);

}
