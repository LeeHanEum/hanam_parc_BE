package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.ApplicationRequestDto;
import hanam.parc.BE.type.dto.ApplicationResponseDto;
import hanam.parc.BE.type.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    @Mappings({
            @Mapping(target = "address", source = "applicationRequestDto.address"),
            @Mapping(target = "guardianName", source = "applicationRequestDto.guardianName"),
            @Mapping(target = "guardianPhone", source = "applicationRequestDto.guardianPhone"),
            @Mapping(target = "remarks", source = "applicationRequestDto.remarks")
    })
    Application ApplicationRequestDtoToApplication(ApplicationRequestDto applicationRequestDto);


    ApplicationResponseDto ApplicationToApplicationResponseDto(Application Application);

}
