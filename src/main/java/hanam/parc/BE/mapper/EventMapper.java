package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.EventRequestDto;
import hanam.parc.BE.type.dto.EventResponseDto;
import hanam.parc.BE.type.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mappings({
            @Mapping(target = "title", source = "eventRequestDto.title"),
            @Mapping(target = "start", source = "eventRequestDto.start"),
            @Mapping(target = "end", source = "eventRequestDto.end"),
            @Mapping(target = "color", source = "eventRequestDto.color"),
            @Mapping(target = "description", source = "eventRequestDto.description")
    })
    Event EventRequestDtoToEvent(EventRequestDto eventRequestDto);

    @Mappings({
            @Mapping(target = "id", source = "event.id"),
            @Mapping(target = "title", source = "event.title"),
            @Mapping(target = "start", source = "event.start"),
            @Mapping(target = "end", source = "event.end"),
            @Mapping(target = "color", source = "event.color"),
            @Mapping(target = "description", source = "event.description"),
            @Mapping(target = "board", source = "event.board"),
            @Mapping(target = "member", source = "event.member"),
            @Mapping(target = "createdAt", source = "event.createdAt"),
            @Mapping(target = "updatedAt", source = "event.updatedAt")
    })
    EventResponseDto EventToEventResponseDto(Event event);
}
