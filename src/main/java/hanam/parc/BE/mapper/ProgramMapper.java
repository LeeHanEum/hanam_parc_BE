package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.ProgramDto;
import hanam.parc.BE.type.entity.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);
    @Mappings({
            @Mapping(target = "name", source = "programDto.name"),
            @Mapping(target = "thumbnail", source = "programDto.thumbnail"),
            @Mapping(target = "category", source = "programDto.category"),
            @Mapping(target = "available", source = "programDto.available"),
            @Mapping(target = "programStatus", source = "programDto.programStatus"),
            @Mapping(target = "disabilityType", source = "programDto.disabilityType"),
            @Mapping(target = "applyPeriod", source = "programDto.applyPeriod"),
            @Mapping(target = "applyMethod", source = "programDto.applyMethod"),
            @Mapping(target = "startDate", source = "programDto.startDate"),
            @Mapping(target = "endDate", source = "programDto.endDate"),
            @Mapping(target = "educationTime", source = "programDto.educationTime"),
            @Mapping(target = "location", source = "programDto.location"),
            @Mapping(target = "cost", source = "programDto.cost"),
            @Mapping(target = "material", source = "programDto.material"),
            @Mapping(target = "description", source = "programDto.description")
    })
    Program ProgramDtoToProgram(ProgramDto programDto);

    @Mappings({
            @Mapping(target = "name", source = "program.name"),
            @Mapping(target = "thumbnail", source = "program.thumbnail"),
            @Mapping(target = "category", source = "program.category"),
            @Mapping(target = "available", source = "program.available"),
            @Mapping(target = "programStatus", source = "program.programStatus"),
            @Mapping(target = "disabilityType", source = "program.disabilityType"),
            @Mapping(target = "applyPeriod", source = "program.applyPeriod"),
            @Mapping(target = "applyMethod", source = "program.applyMethod"),
            @Mapping(target = "startDate", source = "program.startDate"),
            @Mapping(target = "endDate", source = "program.endDate"),
            @Mapping(target = "educationTime", source = "program.educationTime"),
            @Mapping(target = "location", source = "program.location"),
            @Mapping(target = "cost", source = "program.cost"),
            @Mapping(target = "material", source = "program.material"),
            @Mapping(target = "description", source = "program.description")
    })
    ProgramDto ProgramToProgramDto(Program program);

}
