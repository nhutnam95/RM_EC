package com.example.demo.mapper;

import com.example.demo.dto.Rfr_New_FE_Dto;
import com.example.demo.model.Rfr_New;
import javax.annotation.Generated;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class RfrMapperImpl implements RfrMapper {

    @Override
    public Rfr_New_FE_Dto rfr_NewToRfr_New_FE_Dto(Rfr_New rfr) {
        if ( rfr == null ) {
            return null;
        }

        Rfr_New_FE_Dto rfr_New_FE_Dto = new Rfr_New_FE_Dto();

        rfr_New_FE_Dto.setRfrId( rfr.getRfrId() );
        rfr_New_FE_Dto.setRoleName( rfr.getRoleName() );
        rfr_New_FE_Dto.setRankName( rfr.getRankName() );
        rfr_New_FE_Dto.setSkillName( rfr.getSkillName() );
        rfr_New_FE_Dto.setLocationName( rfr.getLocationName() );
        JSONObject jSONObject = rfr.getRfrEffortMonths();
        if ( jSONObject != null ) {
            rfr_New_FE_Dto.setRfrEffortMonths( new JSONObject( jSONObject ) );
        }
        else {
            rfr_New_FE_Dto.setRfrEffortMonths( null );
        }

        return rfr_New_FE_Dto;
    }

    @Override
    public Rfr_New rfr_New_FE_DtotoRfr_New(Rfr_New_FE_Dto rfr_New_FE_Dto) {
        if ( rfr_New_FE_Dto == null ) {
            return null;
        }

        Rfr_New rfr_New = new Rfr_New();

        rfr_New.setRfrId( rfr_New_FE_Dto.getRfrId() );
        rfr_New.setRoleName( rfr_New_FE_Dto.getRoleName() );
        rfr_New.setRankName( rfr_New_FE_Dto.getRankName() );
        rfr_New.setSkillName( rfr_New_FE_Dto.getSkillName() );
        rfr_New.setLocationName( rfr_New_FE_Dto.getLocationName() );
        JSONObject jSONObject = rfr_New_FE_Dto.getRfrEffortMonths();
        if ( jSONObject != null ) {
            rfr_New.setRfrEffortMonths( new JSONObject( jSONObject ) );
        }
        else {
            rfr_New.setRfrEffortMonths( null );
        }

        return rfr_New;
    }
}
