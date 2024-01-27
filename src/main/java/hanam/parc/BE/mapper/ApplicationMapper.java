package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.ApplicationDto;
import hanam.parc.BE.type.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    @Mappings({
            @Mapping(target = "address", source = "applicationDto.address"),
            @Mapping(target = "guardianName", source = "applicationDto.guardianName"),
            @Mapping(target = "guardianPhone", source = "applicationDto.guardianPhone"),
            @Mapping(target = "remarks", source = "applicationDto.remarks")
    })
    Application ApplicationDtoToApplication(ApplicationDto applicationDto);

    @Mappings({
            @Mapping(target = "address", source = "application.address"),
            @Mapping(target = "guardianName", source = "application.guardianName"),
            @Mapping(target = "guardianPhone", source = "application.guardianPhone"),
            @Mapping(target = "remarks", source = "application.remarks")
    })
    ApplicationDto ApplicationToApplicationDto(Application application);

}
