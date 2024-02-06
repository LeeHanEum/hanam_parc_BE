package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.QnARequestDto;
import hanam.parc.BE.type.dto.QnAResponseDto;
import hanam.parc.BE.type.entity.QnA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QnAMapper {

    QnAMapper INSTANCE = Mappers.getMapper(QnAMapper.class);

    @Mappings({
            @Mapping(target = "title", source = "qnARequestDto.title"),
            @Mapping(target = "content", source = "qnARequestDto.content"),
    })
    QnA QnARequestDtoToQnA(QnARequestDto qnARequestDto);

    @Mappings({
            @Mapping(target = "id", source = "qnA.id"),
            @Mapping(target = "title", source = "qnA.title"),
            @Mapping(target = "content", source = "qnA.content"),
            @Mapping(target = "isAnswered", source = "qnA.isAnswered"),
            @Mapping(target = "writer", source = "qnA.writer"),
            @Mapping(target = "answer", source = "qnA.answer"),
            @Mapping(target = "answerer", source = "qnA.answerer"),
            @Mapping(target = "createdAt", source = "qnA.createdAt"),
            @Mapping(target = "updatedAt", source = "qnA.updatedAt")
    })
    QnAResponseDto QnAToQnAResponseDto(QnA qnA);
}
