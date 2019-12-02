package com.example.demo.mapper;

import org.mapstruct.Mapper;
import com.example.demo.dto.Rfr_New_FE_Dto;
import com.example.demo.model.Rfr_New;

@Mapper(componentModel = "spring")
public interface RfrMapper {

	Rfr_New_FE_Dto rfr_NewToRfr_New_FE_Dto(Rfr_New rfr);
	Rfr_New rfr_New_FE_DtotoRfr_New(Rfr_New_FE_Dto rfr_New_FE_Dto);
}
