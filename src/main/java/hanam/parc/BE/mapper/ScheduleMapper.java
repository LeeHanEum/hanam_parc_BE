package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.ScheduleRequestDto;
import hanam.parc.BE.type.dto.ScheduleResponseDto;
import hanam.parc.BE.type.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mappings({
            @Mapping(target = "title", source = "scheduleRequestDto.title"),
            @Mapping(target = "startDate", source = "scheduleRequestDto.startDate"),
            @Mapping(target = "endDate", source = "scheduleRequestDto.endDate"),
            @Mapping(target = "color", source = "scheduleRequestDto.color"),
            @Mapping(target = "description", source = "scheduleRequestDto.description")
    })
    Schedule ScheduleRequestDtoToSchedule(ScheduleRequestDto scheduleRequestDto);

    @Mappings({
            @Mapping(target = "id", source = "schedule.id"),
            @Mapping(target = "title", source = "schedule.title"),
            @Mapping(target = "startDate", source = "schedule.startDate"),
            @Mapping(target = "endDate", source = "schedule.endDate"),
            @Mapping(target = "color", source = "schedule.color"),
            @Mapping(target = "description", source = "schedule.description"),
            @Mapping(target = "board", source = "schedule.board"),
            @Mapping(target = "manager", source = "schedule.manager"),
            @Mapping(target = "createdAt", source = "schedule.createdAt"),
            @Mapping(target = "updatedAt", source = "schedule.updatedAt")
    })
    ScheduleResponseDto ScheduleToScheduleResponseDto(Schedule schedule);
}
