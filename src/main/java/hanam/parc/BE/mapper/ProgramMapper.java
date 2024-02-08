package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.ProgramRequestDto;
import hanam.parc.BE.type.dto.ProgramResponseDto;
import hanam.parc.BE.type.entity.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);
    @Mappings({
            @Mapping(target = "name", source = "programRequestDto.name"),
            @Mapping(target = "thumbnail", source = "programRequestDto.thumbnail"),
            @Mapping(target = "category", source = "programRequestDto.category"),
            @Mapping(target = "available", source = "programRequestDto.available"),
            @Mapping(target = "programStatus", source = "programRequestDto.programStatus"),
            @Mapping(target = "manager", source = "programRequestDto.manager"),
            @Mapping(target = "applyEnd", source = "programRequestDto.applyEnd"),
            @Mapping(target = "startDate", source = "programRequestDto.startDate"),
            @Mapping(target = "endDate", source = "programRequestDto.endDate"),
            @Mapping(target = "educationTime", source = "programRequestDto.educationTime"),
            @Mapping(target = "location", source = "programRequestDto.location"),
            @Mapping(target = "cost", source = "programRequestDto.cost"),
            @Mapping(target = "material", source = "programRequestDto.material"),
            @Mapping(target = "description", source = "programRequestDto.description"),
            @Mapping(target = "createdAt", source = "programRequestDto.createdAt"),
            @Mapping(target = "updatedAt", source = "programRequestDto.updatedAt")
    })
    Program ProgramRequestDtoToProgram(ProgramRequestDto programRequestDto);


    @Mappings({
            @Mapping(target = "id", source = "program.id"),
            @Mapping(target = "name", source = "program.name"),
            @Mapping(target = "thumbnail", source = "program.thumbnail"),
            @Mapping(target = "category", source = "program.category"),
            @Mapping(target = "available", source = "program.available"),
            @Mapping(target = "programStatus", source = "program.programStatus"),
            @Mapping(target = "manager", source = "program.manager"),
            @Mapping(target = "applyEnd", source = "program.applyEnd"),
            @Mapping(target = "startDate", source = "program.startDate"),
            @Mapping(target = "endDate", source = "program.endDate"),
            @Mapping(target = "educationTime", source = "program.educationTime"),
            @Mapping(target = "location", source = "program.location"),
            @Mapping(target = "cost", source = "program.cost"),
            @Mapping(target = "material", source = "program.material"),
            @Mapping(target = "description", source = "program.description"),
            @Mapping(target = "createdAt", source = "program.createdAt"),
            @Mapping(target = "updatedAt", source = "program.updatedAt")
    })
    ProgramResponseDto ProgramToProgramResponseDto(Program program);

}
