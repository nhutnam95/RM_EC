package com.example.demo.mapper;

import com.example.demo.dto.RapDto;
import com.example.demo.dto.Rap_FE_Dto;
import com.example.demo.model.Rap;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class RapMapperImpl implements RapMapper {

    @Override
    public RapDto rapToRapDto(Rap rap) {
        if ( rap == null ) {
            return null;
        }

        RapDto rapDto = new RapDto();

        rapDto.setRapId( rap.getRapId() );
        rapDto.setProjectId( rap.getProjectId() );
        rapDto.setRfrId( rap.getRfrId() );
        rapDto.setAccountName( rap.getAccountName() );
        rapDto.setFrom( rap.getFrom() );
        rapDto.setTo( rap.getTo() );
        rapDto.setFte( rap.getFte() );
        rapDto.setStatus( rap.getStatus() );
        rapDto.setRapCreateDate( rap.getRapCreateDate() );
        rapDto.setRapUpdateDate( rap.getRapUpdateDate() );
        rapDto.setRapDelFlag( rap.isRapDelFlag() );

        return rapDto;
    }

    @Override
    public Rap_FE_Dto rapToRap_FE_Dto(Rap rap) {
        if ( rap == null ) {
            return null;
        }

        Rap_FE_Dto rap_FE_Dto = new Rap_FE_Dto();

        rap_FE_Dto.setRfrId( rap.getRfrId() );
        rap_FE_Dto.setAccountName( rap.getAccountName() );
        rap_FE_Dto.setFrom( rap.getFrom() );
        rap_FE_Dto.setTo( rap.getTo() );
        if ( rap.getFte() != null ) {
            rap_FE_Dto.setFte( rap.getFte() );
        }
        rap_FE_Dto.setStatus( rap.getStatus() );

        return rap_FE_Dto;
    }

    @Override
    public Rap rapDtoToRap(RapDto rapDto) {
        if ( rapDto == null ) {
            return null;
        }

        Rap rap = new Rap();

        rap.setRapId( rapDto.getRapId() );
        rap.setProjectId( rapDto.getProjectId() );
        rap.setRfrId( rapDto.getRfrId() );
        rap.setAccountName( rapDto.getAccountName() );
        rap.setFrom( rapDto.getFrom() );
        rap.setTo( rapDto.getTo() );
        rap.setFte( rapDto.getFte() );
        rap.setStatus( rapDto.getStatus() );
        rap.setRapCreateDate( rapDto.getRapCreateDate() );
        rap.setRapUpdateDate( rapDto.getRapUpdateDate() );
        rap.setRapDelFlag( rapDto.isRapDelFlag() );

        return rap;
    }
}
