package com.example.demo.mapper;

import com.example.demo.dto.RfrEffortMonthDto;
import com.example.demo.dto.RfrEffortMonth_FE_Dto;
import com.example.demo.model.RfrEffortMonth;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class RfrEffortMonthMapperImpl implements RfrEffortMonthMapper {

    @Override
    public RfrEffortMonthDto rfrEffortMonthToRfrEffortMonthDto(RfrEffortMonth rfrEffortMonth) {
        if ( rfrEffortMonth == null ) {
            return null;
        }

        RfrEffortMonthDto rfrEffortMonthDto = new RfrEffortMonthDto();

        rfrEffortMonthDto.setRfrEffortmonthId( rfrEffortMonth.getRfrEffortmonthId() );
        rfrEffortMonthDto.setRfrId( rfrEffortMonth.getRfrId() );
        rfrEffortMonthDto.setEffortName( rfrEffortMonth.getEffortName() );
        rfrEffortMonthDto.setEffort( rfrEffortMonth.getEffort() );

        return rfrEffortMonthDto;
    }

    @Override
    public RfrEffortMonth_FE_Dto rfrEffortMonthToRfrEffortMonth_FE_Dto(RfrEffortMonth rfrEffortMonth) {
        if ( rfrEffortMonth == null ) {
            return null;
        }

        RfrEffortMonth_FE_Dto rfrEffortMonth_FE_Dto = new RfrEffortMonth_FE_Dto();

        rfrEffortMonth_FE_Dto.setEffortName( rfrEffortMonth.getEffortName() );
        rfrEffortMonth_FE_Dto.setEffort( rfrEffortMonth.getEffort() );

        return rfrEffortMonth_FE_Dto;
    }

    @Override
    public RfrEffortMonth rfrEffortMonthDtoToRfrEffortMonth(RfrEffortMonthDto rfrEffortMonthDto) {
        if ( rfrEffortMonthDto == null ) {
            return null;
        }

        RfrEffortMonth rfrEffortMonth = new RfrEffortMonth();

        rfrEffortMonth.setRfrEffortmonthId( rfrEffortMonthDto.getRfrEffortmonthId() );
        rfrEffortMonth.setRfrId( rfrEffortMonthDto.getRfrId() );
        rfrEffortMonth.setEffortName( rfrEffortMonthDto.getEffortName() );
        rfrEffortMonth.setEffort( rfrEffortMonthDto.getEffort() );

        return rfrEffortMonth;
    }
}
